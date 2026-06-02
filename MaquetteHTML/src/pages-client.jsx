// PREDICT'IF — Pages 4-5: Accueil Client + Historique (v2)
const { useState: useStateClient, useMemo: useMemoClient } = React;

// ————————————————————————————————————————————————————————
// Logique d'assignation employé selon les 3 critères du brief :
// 1. Pas en consultation
// 2. Genre correspondant au médium
// 3. Moins de consultations (équité)
// ————————————————————————————————————————————————————————
function assignerEmploye(medium) {
  const all = window.PREDICTIF_DATA.employes;
  let pool = all.filter(e => !e.enConsultation);
  if (pool.length === 0) return null;
  // Filter by genre (NON_SPECIFIE matches all)
  if (medium.genre !== 'NON_SPECIFIE') {
    const matched = pool.filter(e => e.genre === medium.genre);
    if (matched.length > 0) pool = matched;
    else return null; // no compatible genre available
  }
  // Pick the one with fewest consultations
  pool = [...pool].sort((a, b) => a.consultations - b.consultations);
  return pool[0];
}

// ————————————————————————————————————————————————————————
// PAGE 4 — Accueil Client
// ————————————————————————————————————————————————————————
function ClientHomePage({ go, pushToast, addConsultation }) {
  const data = window.PREDICTIF_DATA;
  const [filter, setFilter] = useStateClient('Tous');
  const [requesting, setRequesting] = useStateClient(null);

  const filtered = useMemoClient(() => {
    if (filter === 'Tous') return data.mediums;
    return data.mediums.filter(m => m.type === filter);
  }, [filter]);

  const requestConsult = (medium) => {
    setRequesting(medium.id);
    setTimeout(() => {
      const employe = assignerEmploye(medium);
      if (!employe) {
        pushToast({
          title: 'Aucune voix disponible',
          msg: `Aucun médium ne peut incarner ${medium.denomination} en ce moment. Le seuil rouvrira sous peu — réessayez bientôt.`,
          error: true
        });
      } else {
        // Simulate SMS to client with medium's phone number
        pushToast({
          title: 'SMS · Consultation confirmée',
          msg: `${medium.denomination} (${medium.type}) vous attend. Appelez le ${medium.telephone} lorsque vous êtes prête. Le seuil s'ouvre.`,
          sms: true,
          mediumName: medium.denomination,
          mediumPhone: medium.telephone,
        });
        addConsultation({
          id: 'c-' + Date.now(),
          date: '24 mai 2026',
          dateLong: "24 mai 2026, à l'heure d'or",
          medium: medium.denomination,
          mediumId: medium.id,
          type: medium.type,
          status: 'pending',
          commentaire: '',
          assignedEmploye: `${employe.prenom} ${employe.nom}`,
        });
      }
      setRequesting(null);
    }, 1100);
  };

  return (
    <div className="page">
      <Particles />
      <PageHeading
        eyebrow={`Bienvenue, ${data.client.prenom}`}
        title="Votre cabinet"
        sub="Votre carte natale est ouverte. Choisissez la voix qui répondra ce soir."
      />

      <AstralProfileCard realData />

      <div className="section-head" style={{ marginTop: 60 }}>
        <div className="left">
          <span className="eyebrow">Choisir une voix</span>
          <h2>Les six médiums</h2>
        </div>
        <div className="chips">
          {['Tous', 'Spirite', 'Cartomancien', 'Astrologue'].map(t => (
            <button key={t} className={`chip ${filter===t?'active':''}`} onClick={()=>setFilter(t)}>{t}</button>
          ))}
        </div>
      </div>

      <div className="grid-3 grid-mediums">
        {filtered.map(m => (
          <div key={m.id} style={{ position: 'relative', display: 'flex' }}>
            <TarotCard
              medium={m}
              onChoose={requestConsult}
              available={requesting === null}
            />
            {requesting === m.id && (
              <div style={{
                position:'absolute', inset:0, background:'rgba(10,10,15,0.78)', backdropFilter:'blur(2px)',
                display:'flex', flexDirection:'column', alignItems:'center', justifyContent:'center', gap:12, zIndex:5
              }}>
                <div className="spinner" />
                <div className="eyebrow">Recherche d'une voix disponible…</div>
                <div style={{ fontFamily:'var(--font-mono)', fontSize:'0.7rem', color:'var(--ink-muted)', textAlign:'center', maxWidth:200 }}>
                  Critères · disponibilité · genre · équité
                </div>
              </div>
            )}
          </div>
        ))}
      </div>

      <div style={{ display:'flex', justifyContent:'center', marginTop: 60 }}>
        <button className="btn btn-ghost" onClick={()=>go('client-history')}>
          <Sigil.scroll style={{ width:16, height:16 }} />
          Voir l'historique de mes consultations
        </button>
      </div>
    </div>
  );
}

// ————————————————————————————————————————————————————————
// PAGE 5 — Historique Client
// ————————————————————————————————————————————————————————
function ClientHistoryPage({ go, consultations }) {
  const [opened, setOpened] = useStateClient(null);

  return (
    <div className="page">
      <Particles />
      <PageHeading
        eyebrow="Le grimoire des séances"
        title="Historique des consultations"
        sub="Chaque lame tirée laisse une trace. Voici les vôtres, dans l'ordre où la nuit les a vues."
      />

      <div style={{ maxWidth: 880, margin: '0 auto' }}>
        {consultations.length === 0 && (
          <div style={{ textAlign:'center', color:'var(--ink-muted)', padding:'80px 20px', fontStyle:'italic' }}>
            Le grimoire est encore vierge. Demandez votre première consultation depuis l'accueil.
          </div>
        )}
        {consultations.map(c => {
          const [d, m] = c.date.split(' ');
          return (
            <div key={c.id} className="consult-row" onClick={() => setOpened(c)}>
              <div className="date">
                <div className="day">{d}</div>
                <div className="mon">{m.replace('.', '')}</div>
              </div>
              <div>
                <div className="name">{c.medium}</div>
                <div className="sub">{c.type} · {c.dateLong}</div>
              </div>
              <div className={`status-pill ${c.status}`}>
                {c.status === 'done' ? 'Achevée' : c.status === 'pending' ? 'À venir' : 'En cours'}
              </div>
              <Sigil.arrow style={{ width:18, height:18, color:'var(--gold-1)' }} />
            </div>
          );
        })}
      </div>

      <div style={{ display:'flex', justifyContent:'center', marginTop: 60 }}>
        <button className="btn btn-ghost" onClick={()=>go('client-home')}>
          <Sigil.arrow style={{ width:16, height:16, transform:'scaleX(-1)' }} />
          Retour au cabinet
        </button>
      </div>

      {opened && (
        <div className="modal-bg" onClick={() => setOpened(null)}>
          <div className="modal" onClick={e=>e.stopPropagation()}>
            <Sigil.corner className="corner" style={{ position:'absolute', top:14, left:14, width:24, height:24, color:'var(--gold-1)' }} />
            <Sigil.corner className="corner" style={{ position:'absolute', top:14, right:14, width:24, height:24, color:'var(--gold-1)', transform:'scaleX(-1)' }} />
            <div style={{ textAlign:'center', marginBottom:24 }}>
              <div className="eyebrow">Séance n° {opened.id.slice(-4)}</div>
              <h2 style={{ marginTop:8 }}>{opened.medium}</h2>
              <div style={{ color:'var(--ink-muted)', fontStyle:'italic', marginTop:6 }}>{opened.dateLong}</div>
            </div>
            <div style={{ borderTop:'1px solid rgba(201,168,76,0.2)', borderBottom:'1px solid rgba(201,168,76,0.2)', padding:'20px 0', textAlign:'center' }}>
              <div className="eyebrow">{opened.type}</div>
              <div className={`status-pill ${opened.status}`} style={{ display:'inline-block', marginTop:10 }}>
                {opened.status === 'done' ? 'Séance achevée' : opened.status === 'pending' ? 'En attente' : 'En cours'}
              </div>
            </div>
            <div style={{ marginTop: 24 }}>
              <div className="eyebrow" style={{ marginBottom: 10 }}>Commentaire du médium</div>
              {opened.commentaire ? (
                <p style={{ fontStyle:'italic', color:'var(--ink-bone)', lineHeight: 1.6, fontSize:'1.05rem' }}>
                  « {opened.commentaire} »
                </p>
              ) : (
                <p style={{ color:'var(--ink-muted)', fontStyle:'italic' }}>
                  Aucun commentaire n'a été consigné pour cette séance.
                </p>
              )}
            </div>
            <button className="btn btn-ghost" style={{ marginTop: 30, width:'100%', justifyContent:'center' }} onClick={()=>setOpened(null)}>
              Refermer le grimoire
            </button>
          </div>
        </div>
      )}
    </div>
  );
}

Object.assign(window, { ClientHomePage, ClientHistoryPage, assignerEmploye });
