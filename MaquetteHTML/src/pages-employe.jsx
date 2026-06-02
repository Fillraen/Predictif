// PREDICT'IF — Pages 6-10: Employé flow
const { useState: useStateEmp, useEffect: useEffectEmp, useMemo: useMemoEmp } = React;

// ————————————————————————————————————————————————————————
// PAGE 6 — Accueil Employé
// ————————————————————————————————————————————————————————
function EmployeHomePage({ go, assigned }) {
  const data = window.PREDICTIF_DATA;
  const e = data.employe;
  return (
    <div className="page">
      <Particles />
      <PageHeading
        eyebrow={`Bienvenue, ${e.prenom}`}
        title="Cabinet du médium"
        sub="Le système vous attribue les séances dès qu'une cliente appelle. Voici ce qui vous attend."
      />

      {/* Assigned consultation */}
      <div className="section-head">
        <div className="left">
          <span className="eyebrow">Séance assignée</span>
          <h2>Une cliente vous attend</h2>
        </div>
      </div>

      {assigned ? (
        <div style={{
          position:'relative',
          background:'radial-gradient(ellipse at top right, rgba(123, 47, 160, 0.25), transparent 60%), linear-gradient(180deg, var(--bg-card-2), var(--bg-card))',
          border:'1px solid rgba(201, 168, 76, 0.4)',
          padding:'36px 40px',
          display:'grid', gridTemplateColumns:'100px 1fr auto', gap: 24, alignItems:'center'
        }}>
          <Sigil.corner style={{ position:'absolute', top:14, left:14, width:28, height:28, color:'var(--gold-1)' }} />
          <Sigil.corner style={{ position:'absolute', top:14, right:14, width:28, height:28, color:'var(--gold-1)', transform:'scaleX(-1)' }} />
          <Sigil.corner style={{ position:'absolute', bottom:14, left:14, width:28, height:28, color:'var(--gold-1)', transform:'scaleY(-1)' }} />
          <Sigil.corner style={{ position:'absolute', bottom:14, right:14, width:28, height:28, color:'var(--gold-1)', transform:'scale(-1,-1)' }} />

          <div style={{
            width:100, height:100, border:'1px solid rgba(201,168,76,0.5)',
            display:'flex', alignItems:'center', justifyContent:'center',
            background:'radial-gradient(circle, rgba(123,47,160,0.3), transparent 70%)'
          }}>
            <Sigil.bell style={{ width:40, height:40, color:'var(--gold-2)', filter:'drop-shadow(0 0 8px var(--gold-glow))' }} />
          </div>

          <div>
            <div className="eyebrow" style={{ color:'#c89cd8' }}>Notification · Nouvelle séance</div>
            <h3 style={{ fontSize:'1.5rem', marginTop:6 }}>{assigned.clientName}</h3>
            <div style={{ color:'var(--ink-parchment)', marginTop:8, fontSize:'1.02rem' }}>
              Médium à incarner : <strong style={{ color:'var(--gold-2)', fontFamily:'var(--font-display)' }}>{assigned.mediumName}</strong>
              <span style={{ color:'var(--ink-muted)', fontStyle:'italic' }}> · {assigned.mediumType}</span>
            </div>
            <div style={{ color:'var(--ink-muted)', fontStyle:'italic', marginTop:6, fontSize:'0.95rem' }}>
              Demandée {assigned.requestedAt} · Séance prévue à {assigned.scheduledAt}
            </div>
          </div>

          <button className="btn btn-primary" onClick={() => go('employe-detail')}>
            Voir la consultation
            <Sigil.arrow style={{ width:14, height:14 }} />
          </button>
        </div>
      ) : (
        <div style={{ padding:'60px', textAlign:'center', color:'var(--ink-muted)', fontStyle:'italic', border:'1px dashed rgba(201,168,76,0.25)' }}>
          Aucune séance n'est assignée pour l'instant. Le système choisira une voix dès qu'une cliente appellera.
        </div>
      )}

      {/* Stats shortcut */}
      <div style={{ marginTop: 60, maxWidth: 420, marginLeft: 'auto', marginRight: 'auto' }}>
        <ShortcutTile glyph="chart" title="Vos statistiques" sub="Vos consultations, vos médiums favoris" onClick={()=>go('employe-stats')} />
      </div>
    </div>
  );
}

function ShortcutTile({ glyph, title, sub, onClick }) {
  const G = Sigil[glyph];
  return (
    <button onClick={onClick} style={{
      background: 'linear-gradient(180deg, var(--bg-card-2), var(--bg-card))',
      border: '1px solid rgba(201, 168, 76, 0.22)',
      padding: '24px',
      textAlign: 'left',
      cursor: 'pointer',
      transition: 'all 0.3s var(--tx)',
      display:'flex', gap:18, alignItems:'center',
      fontFamily:'var(--font-body)',
      color:'var(--ink-parchment)'
    }}
    onMouseEnter={e=>{ e.currentTarget.style.borderColor='var(--gold-2)'; e.currentTarget.style.transform='translateY(-2px)'; }}
    onMouseLeave={e=>{ e.currentTarget.style.borderColor='rgba(201,168,76,0.22)'; e.currentTarget.style.transform='none'; }}>
      <div style={{ width:42, height:42, color:'var(--gold-2)' }}><G style={{width:'100%', height:'100%'}} /></div>
      <div>
        <div className="eyebrow">{title}</div>
        <div style={{ marginTop:4, color:'var(--ink-muted)', fontStyle:'italic', fontSize:'0.92rem' }}>{sub}</div>
      </div>
    </button>
  );
}

// ————————————————————————————————————————————————————————
// PAGE 7 — Détail de la consultation assignée
// ————————————————————————————————————————————————————————
function EmployeDetailPage({ go, assigned, pushToast }) {
  const data = window.PREDICTIF_DATA;
  const [ready, setReady] = useStateEmp(false);

  if (!assigned) {
    return <div className="page"><PageHeading eyebrow="" title="Aucune séance assignée" /></div>;
  }

  const markReady = () => {
    setReady(true);
    pushToast({ title: 'Notification envoyée', msg: `${assigned.clientName} a été prévenue que vous êtes prête. Le seuil peut s'ouvrir.` });
  };

  return (
    <div className="page">
      <Particles />
      <PageHeading
        eyebrow="Préparation · Séance"
        title={assigned.mediumName}
        sub={`Avant d'incarner ${assigned.mediumName}, prenez le temps d'étudier la cliente.`}
      />

      <div style={{ display:'grid', gridTemplateColumns:'repeat(auto-fit, minmax(360px, 1fr))', gap: 32 }}>
        {/* Profil astral */}
        <div>
          <div className="eyebrow" style={{ marginBottom:14 }}>Profil de la cliente</div>
          <AstralProfileCard realData />
          {(() => {
            const a = data.client.adresse;
            const dep = (data.departements || []).find(d => d.code === a.departement);
            return (
              <div style={{ marginTop:18, fontSize:'0.92rem', color:'var(--ink-muted)', fontStyle:'italic', textAlign:'center', padding:'0 20px', lineHeight:1.7 }}>
                Adresse · {a.numero} {a.voie}, {a.ville}<br/>
                {dep ? `${dep.nom} (${dep.code})` : a.departement} · {a.pays}<br/>
                Téléphone · {data.client.telephone}
              </div>
            );
          })()}
        </div>

        {/* Historique court */}
        <div>
          <div className="eyebrow" style={{ marginBottom:14 }}>Précédentes séances de la cliente</div>
          <div style={{
            background:'linear-gradient(180deg, var(--bg-card-2), var(--bg-card))',
            border:'1px solid rgba(201,168,76,0.25)',
            padding:'8px 0'
          }}>
            {data.consultations.slice(0,4).map(c => (
              <div key={c.id} style={{
                padding:'16px 22px',
                borderBottom:'1px solid rgba(201, 168, 76, 0.10)',
                display:'flex', justifyContent:'space-between', alignItems:'center'
              }}>
                <div>
                  <div style={{ fontFamily:'var(--font-display)', fontSize:'1.05rem', color:'var(--ink-bone)' }}>{c.medium}</div>
                  <div style={{ fontSize:'0.85rem', color:'var(--ink-muted)', fontStyle:'italic', marginTop:2 }}>{c.dateLong}</div>
                  {c.commentaire && (
                    <div style={{ fontSize:'0.88rem', color:'var(--ink-parchment)', marginTop:8, fontStyle:'italic', lineHeight:1.5, paddingLeft: 14, borderLeft:'2px solid rgba(201, 168, 76, 0.3)' }}>
                      « {c.commentaire.length > 140 ? c.commentaire.slice(0,140)+'…' : c.commentaire} »
                    </div>
                  )}
                </div>
              </div>
            ))}
          </div>

          {/* Action buttons */}
          <div style={{ marginTop: 32, display:'flex', flexDirection:'column', gap: 14 }}>
            {!ready ? (
              <button className="btn btn-violet" style={{ justifyContent:'center', padding:'18px' }} onClick={markReady}>
                <Sigil.flame style={{ width:18, height:18 }} />
                Je suis prête
              </button>
            ) : (
              <div style={{
                padding:'18px 22px',
                background:'rgba(111, 232, 160, 0.06)',
                border:'1px solid rgba(111, 232, 160, 0.3)',
                color:'#aef0c5',
                fontFamily:'var(--font-display)', fontSize:'0.85rem', letterSpacing:'0.2em', textTransform:'uppercase', textAlign:'center'
              }}>
                <Sigil.check style={{ width:16, height:16, verticalAlign:-3, marginRight:10 }} />
                Cliente notifiée — le seuil s'ouvre
              </div>
            )}
            <button className="btn btn-primary" style={{ justifyContent:'center', padding:'18px' }} disabled={!ready} onClick={()=>go('employe-live')}>
              Commencer la consultation
              <Sigil.arrow style={{ width:16, height:16 }} />
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

// ————————————————————————————————————————————————————————
// PAGE 8 — Consultation en cours (live)
// ————————————————————————————————————————————————————————
function EmployeLivePage({ go, assigned, pushToast }) {
  const data = window.PREDICTIF_DATA;
  const [vals, setVals] = useStateEmp({ amour: 3, sante: 2, travail: 4 });
  const [predictions, setPredictions] = useStateEmp([]);
  const [elapsed, setElapsed] = useStateEmp(0);

  useEffectEmp(() => {
    const t = setInterval(() => setElapsed(e => e + 1), 1000);
    return () => clearInterval(t);
  }, []);

  const generate = () => {
    const pick = (arr, n) => arr[Math.min(arr.length-1, Math.max(0, n-1))];
    const newPreds = [
      { domain: 'Amour', text: pick(data.inspirations.amour, vals.amour), level: vals.amour, glyph: 'flame' },
      { domain: 'Santé', text: pick(data.inspirations.sante, vals.sante), level: vals.sante, glyph: 'moon' },
      { domain: 'Travail', text: pick(data.inspirations.travail, vals.travail), level: vals.travail, glyph: 'sun' },
    ];
    setPredictions(newPreds);
  };

  const validate = () => {
    pushToast({ title: 'Séance validée', msg: 'Le voile retombe doucement. Laissez maintenant une note à vos collègues.' });
    setTimeout(()=>go('employe-comment'), 600);
  };

  const fmt = (s) => `${String(Math.floor(s/60)).padStart(2,'0')}:${String(s%60).padStart(2,'0')}`;

  return (
    <div className="page" style={{ padding: '40px 56px 80px' }}>
      <Particles />

      {/* Live header */}
      <div style={{
        display:'flex', justifyContent:'space-between', alignItems:'center',
        padding:'18px 24px', marginBottom: 32,
        background:'linear-gradient(180deg, rgba(74, 14, 110, 0.20), transparent)',
        border:'1px solid rgba(123, 47, 160, 0.4)',
      }}>
        <div style={{ display:'flex', alignItems:'center', gap: 18 }}>
          <span style={{
            width:10, height:10, borderRadius:'50%', background:'#6fe8a0',
            boxShadow:'0 0 12px #6fe8a0', animation:'pulse-glow 1.5s ease-in-out infinite'
          }} />
          <div>
            <div className="eyebrow" style={{ color:'#aef0c5' }}>Séance en cours</div>
            <div style={{ fontFamily:'var(--font-display)', fontSize:'1.1rem', marginTop:2 }}>
              {assigned?.clientName} · {assigned?.mediumName}
            </div>
          </div>
        </div>
        <div style={{ textAlign:'right' }}>
          <div className="eyebrow">Durée</div>
          <div style={{ fontFamily:'var(--font-mono)', fontSize:'1.5rem', color:'var(--gold-2)', marginTop:2 }}>{fmt(elapsed)}</div>
        </div>
      </div>

      <div style={{ display:'grid', gridTemplateColumns:'repeat(auto-fit, minmax(360px, 1fr))', gap: 32 }}>
        {/* Inspiration generator */}
        <div>
          <div className="eyebrow" style={{ marginBottom:8 }}>Inspiration · Échelle du bonheur</div>
          <h3 style={{ marginBottom: 18, color:'var(--ink-bone)' }}>Trois domaines, quatre lames</h3>
          <div style={{
            background:'linear-gradient(180deg, var(--bg-card-2), var(--bg-card))',
            border:'1px solid rgba(201,168,76,0.25)',
            padding:'10px 26px 20px'
          }}>
            <BonheurSlider label="Amour" value={vals.amour} setValue={v => setVals(s => ({...s, amour:v}))} glyph="flame" />
            <BonheurSlider label="Santé" value={vals.sante} setValue={v => setVals(s => ({...s, sante:v}))} glyph="moon" />
            <BonheurSlider label="Travail" value={vals.travail} setValue={v => setVals(s => ({...s, travail:v}))} glyph="sun" />
          </div>
          <button className="btn btn-violet" style={{ width:'100%', justifyContent:'center', marginTop: 18 }} onClick={generate}>
            <Sigil.crystal style={{ width:18, height:18 }} />
            Obtenir les prédictions
          </button>
        </div>

        {/* Predictions */}
        <div>
          <div className="eyebrow" style={{ marginBottom:8 }}>Lecture · Prédictions</div>
          <h3 style={{ marginBottom: 18, color:'var(--ink-bone)' }}>Ce que les lames murmurent</h3>
          {predictions.length === 0 ? (
            <div style={{
              padding:'80px 30px', textAlign:'center',
              border:'1px dashed rgba(201,168,76,0.25)',
              color:'var(--ink-muted)', fontStyle:'italic'
            }}>
              <Sigil.crystal style={{ width:48, height:48, color:'var(--gold-deep)', marginBottom:18 }} />
              <div>La sphère est encore claire. Ajustez les sliders puis invoquez les prédictions.</div>
            </div>
          ) : (
            predictions.map((p, i) => {
              const G = Sigil[p.glyph];
              return (
                <div className="prediction" key={i + '-' + p.text}>
                  <G className="glyph" />
                  <div style={{ flex:1 }}>
                    <div className="domain">
                      {p.domain}
                      <span style={{ marginLeft:14, fontFamily:'var(--font-mono)', color:'var(--gold-2)' }}>
                        {Array.from({length:4}).map((_,k) => k < p.level ? '◆' : '◇').join(' ')}
                      </span>
                    </div>
                    <div className="text">« {p.text} »</div>
                  </div>
                </div>
              );
            })
          )}
        </div>
      </div>

      <div style={{ marginTop: 50, display:'flex', justifyContent:'center', gap: 14 }}>
        <button className="btn btn-ghost" onClick={()=>go('employe-detail')}>
          <Sigil.pause style={{ width:14, height:14 }} />
          Suspendre la séance
        </button>
        <button className="btn btn-primary" onClick={validate}>
          Valider et terminer la consultation
          <Sigil.check style={{ width:16, height:16 }} />
        </button>
      </div>
    </div>
  );
}

function BonheurSlider({ label, value, setValue, glyph }) {
  const G = Sigil[glyph];
  const RANKS = ['I', 'II', 'III', 'IV'];
  const NAMES = ['Étincelle', 'Flamme', 'Brasier', 'Soleil'];
  return (
    <div className="inspiration-row">
      <div className="inspiration-head">
        <div className="inspiration-label">
          <G />
          {label}
        </div>
        <div className="inspiration-pick">Note · {value}/4 · {NAMES[value-1]}</div>
      </div>
      <div className="tarot-pips">
        {[1,2,3,4].map(n => (
          <button
            type="button"
            key={n}
            className={`tarot-pip ${n === value ? 'active' : ''}`}
            onClick={() => setValue(n)}
          >
            <div className="pip-rank">{RANKS[n-1]}</div>
            <div className="pip-glyphs">{'◆'.repeat(n)}</div>
            <div className="pip-name">{NAMES[n-1]}</div>
          </button>
        ))}
      </div>
    </div>
  );
}

// ————————————————————————————————————————————————————————
// PAGE 9 — Commentaire post-consultation
// ————————————————————————————————————————————————————————
function EmployeCommentPage({ go, pushToast, assigned }) {
  const [text, setText] = useStateEmp('');
  const submit = () => {
    pushToast({ title: 'Note consignée', msg: 'Votre commentaire a été ajouté au grimoire. Vos collègues et la cliente pourront en prendre connaissance.' });
    setTimeout(()=>go('employe-home'), 600);
  };
  return (
    <div className="page">
      <Particles />
      <PageHeading
        eyebrow="Après la séance"
        title="Note pour les collègues"
        sub="Une page du grimoire vous est ouverte. Ce que vous y consignerez sera visible par vos collèges médiums et par la cliente."
      />

      <div style={{ maxWidth: 760, margin: '0 auto' }}>
        <div style={{
          background:'linear-gradient(180deg, var(--bg-card-2), var(--bg-card))',
          border:'1px solid rgba(201,168,76,0.35)',
          padding: 36,
          position:'relative'
        }}>
          <Sigil.corner style={{ position:'absolute', top:12, left:12, width:24, height:24, color:'var(--gold-1)' }} />
          <Sigil.corner style={{ position:'absolute', top:12, right:12, width:24, height:24, color:'var(--gold-1)', transform:'scaleX(-1)' }} />
          <Sigil.corner style={{ position:'absolute', bottom:12, left:12, width:24, height:24, color:'var(--gold-1)', transform:'scaleY(-1)' }} />
          <Sigil.corner style={{ position:'absolute', bottom:12, right:12, width:24, height:24, color:'var(--gold-1)', transform:'scale(-1,-1)' }} />

          <div style={{ display:'flex', justifyContent:'space-between', alignItems:'center', marginBottom:24, padding:'0 4px' }}>
            <div>
              <div className="eyebrow">Cliente</div>
              <div style={{ fontFamily:'var(--font-display)', fontSize:'1.2rem', marginTop:4 }}>{assigned?.clientName}</div>
            </div>
            <div style={{ textAlign:'right' }}>
              <div className="eyebrow">Médium incarné</div>
              <div style={{ fontFamily:'var(--font-display)', fontSize:'1.2rem', marginTop:4, color:'var(--gold-2)' }}>{assigned?.mediumName}</div>
            </div>
          </div>

          <textarea
            value={text}
            onChange={e=>setText(e.target.value)}
            placeholder="Notez ce qui a marqué la séance — une émotion, un thème récurrent, une zone à approfondir lors de la prochaine venue…"
            style={{
              width:'100%', minHeight: 240,
              background:'rgba(0,0,0,0.45)',
              border:'1px solid rgba(201,168,76,0.25)',
              padding:'18px 22px',
              color:'var(--ink-bone)',
              fontFamily:'var(--font-body)',
              fontSize:'1.08rem',
              fontStyle:'italic',
              lineHeight:1.6,
              outline:'none',
              resize:'vertical'
            }}
          />
          <div style={{ display:'flex', justifyContent:'space-between', marginTop:18, alignItems:'center' }}>
            <span style={{ fontFamily:'var(--font-mono)', fontSize:'0.75rem', color:'var(--ink-muted)' }}>
              {text.length} caractères · visible par les autres médiums et par la cliente
            </span>
            <div style={{ display:'flex', gap:12 }}>
              <button className="btn btn-ghost" onClick={()=>go('employe-home')}>Passer</button>
              <button className="btn btn-primary" onClick={submit} disabled={!text.trim()}>
                Soumettre la note
                <Sigil.arrow style={{ width:14, height:14 }} />
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

// ————————————————————————————————————————————————————————
// PAGE 10 — Statistiques
// ————————————————————————————————————————————————————————
function EmployeStatsPage({ go }) {
  const s = window.PREDICTIF_DATA.stats;
  const maxMedium = Math.max(...s.consultationsParMedium.map(x => x.value));

  return (
    <div className="page">
      <Particles />
      <PageHeading
        eyebrow="Le miroir des chiffres"
        title="Statistiques du cabinet"
        sub="Une lecture chiffrée des séances de la maison. Trois miroirs, une seule vérité."
      />

      {/* Carte de France — clientes par département */}
      <FranceClientMap />

      {/* Bar chart — consultations par médium */}
      <div className="stat-tile" style={{ marginBottom: 24 }}>
        <div className="head">
          <div>
            <div className="eyebrow">Volume · Par médium</div>
            <h3 style={{ marginTop:4 }}>Consultations par médium</h3>
          </div>
          <Sigil.chart style={{ width: 22, height: 22, color: 'var(--gold-2)' }} />
        </div>
        <div className="bars">
          {s.consultationsParMedium.map(b => (
            <div key={b.name} className="bar-row" style={{ gridTemplateColumns: '170px 1fr 50px' }}>
              <div className="lbl">{b.name}</div>
              <div className="track"><div className="fill" style={{ width: `${(b.value / maxMedium) * 100}%` }} /></div>
              <div className="val">{b.value}</div>
            </div>
          ))}
        </div>
      </div>

      <div style={{ display:'grid', gridTemplateColumns:'repeat(auto-fit, minmax(360px, 1fr))', gap: 24, marginBottom: 24 }}>
        {/* Camembert: clients par employé */}
        <div className="stat-tile">
          <div className="head">
            <div>
              <div className="eyebrow">Équité · Charge des médiums</div>
              <h3 style={{ marginTop:4 }}>Clients par employé</h3>
            </div>
            <Sigil.eye style={{ width: 22, height: 22, color: 'var(--gold-2)' }} />
          </div>
          <Donut data={s.clientsParEmploye} />
        </div>

        {/* Top 5 ranking */}
        <div className="stat-tile">
          <div className="head">
            <div>
              <div className="eyebrow">Classement · Top 5</div>
              <h3 style={{ marginTop:4 }}>Médiums les plus choisis</h3>
            </div>
            <Sigil.star style={{ width: 22, height: 22, color: 'var(--gold-2)' }} />
          </div>
          <div className="top5-list" style={{ marginTop: 8 }}>
            {s.top5.map(r => (
              <div key={r.rank} className="top5-row">
                <div className={`top5-rank top${r.rank}`}>
                  {['I','II','III','IV','V'][r.rank-1]}
                </div>
                <div className="top5-name">
                  {r.name}
                  <span className="sub">{r.type}</span>
                </div>
                <div className="top5-value">{r.value} séances</div>
                <div className="top5-share">{r.share}%</div>
              </div>
            ))}
          </div>
        </div>
      </div>

      <div style={{ display:'flex', justifyContent:'center', marginTop: 50 }}>
        <button className="btn btn-ghost" onClick={()=>go('employe-home')}>
          <Sigil.arrow style={{ width:14, height:14, transform:'scaleX(-1)' }} />
          Retour au cabinet
        </button>
      </div>
    </div>
  );
}

function FranceClientMap() {
  const data = window.PREDICTIF_DATA;
  const counts = data.stats.clientsParDepartement;
  const deps = data.departements;
  const [hover, setHover] = useStateEmp(null); // { code, nom, value }
  const [ready, setReady] = useStateEmp(false);

  const depName = React.useMemo(() => {
    const m = {};
    deps.forEach(d => { m[d.code] = d.nom; });
    return m;
  }, [deps]);

  const total = React.useMemo(() => Object.values(counts).reduce((a, b) => a + b, 0), [counts]);
  const max = React.useMemo(() => Math.max(...Object.values(counts)), [counts]);

  // 6-step gold ramp on the dark/violet base
  const EMPTY = '#1b1430';
  const RAMP = [
    { upTo: 0,  c: EMPTY },
    { upTo: 2,  c: '#4a2f63' },
    { upTo: 5,  c: '#7a4a78' },
    { upTo: 10, c: '#a8763f' },
    { upTo: 20, c: '#cda63f' },
    { upTo: Infinity, c: '#e8c96a' },
  ];
  const colorFor = (v) => {
    if (!v) return EMPTY;
    for (const step of RAMP) if (v <= step.upTo) return step.c;
    return '#e8c96a';
  };

  const containerRef = React.useRef(null);

  React.useEffect(() => {
    if (!containerRef.current) return;
    const txt = window.PREDICTIF_FRANCE_SVG;
    if (!txt) return;
    containerRef.current.innerHTML = txt;
    const svg = containerRef.current.querySelector('svg');
    if (!svg) return;
    svg.removeAttribute('width');
    svg.removeAttribute('height');
    svg.setAttribute('preserveAspectRatio', 'xMidYMid meet');
    svg.style.width = '100%';
    svg.style.height = '100%';
    svg.style.display = 'block';
    const paths = svg.querySelectorAll('path[data-numerodepartement]');
    paths.forEach(p => {
      const code = p.getAttribute('data-numerodepartement');
      const v = counts[code] || 0;
      p.style.fill = colorFor(v);
      p.style.stroke = 'rgba(10,10,15,0.65)';
      p.style.strokeWidth = '0.6';
      p.style.transition = 'fill 0.2s ease, filter 0.2s ease';
      p.style.cursor = 'pointer';
      p.addEventListener('mouseenter', () => {
        p.style.filter = 'drop-shadow(0 0 5px rgba(232,201,106,0.9))';
        p.style.stroke = 'var(--gold-2)';
        p.style.strokeWidth = '1.1';
        setHover({ code, nom: p.getAttribute('data-nom') || depName[code] || code, value: v });
      });
      p.addEventListener('mouseleave', () => {
        p.style.filter = 'none';
        p.style.stroke = 'rgba(10,10,15,0.65)';
        p.style.strokeWidth = '0.6';
        setHover(null);
      });
    });
    setReady(true);
  }, []);

  // Top departments list
  const top = React.useMemo(() =>
    Object.entries(counts)
      .map(([code, value]) => ({ code, value, nom: depName[code] || code }))
      .sort((a, b) => b.value - a.value)
      .slice(0, 8)
  , [counts, depName]);

  const legend = [
    { label: '0', c: EMPTY },
    { label: '1–2', c: '#4a2f63' },
    { label: '3–5', c: '#7a4a78' },
    { label: '6–10', c: '#a8763f' },
    { label: '11–20', c: '#cda63f' },
    { label: '21 +', c: '#e8c96a' },
  ];

  return (
    <div className="stat-tile" style={{ marginBottom: 24 }}>
      <div className="head">
        <div>
          <div className="eyebrow">Géographie · Clientèle</div>
          <h3 style={{ marginTop: 4 }}>Clientes par département</h3>
        </div>
        <Sigil.eye style={{ width: 22, height: 22, color: 'var(--gold-2)' }} />
      </div>

      <div className="france-map-grid">
        {/* Map */}
        <div className="france-map-wrap">
          <div ref={containerRef} className="france-map-svg" />
          {!ready && (
            <div style={{ position:'absolute', inset:0, display:'flex', alignItems:'center', justifyContent:'center', color:'var(--ink-muted)', fontStyle:'italic' }}>
              <div className="spinner" />
            </div>
          )}
          {/* Hover readout */}
          <div className="france-map-readout">
            {hover ? (
              <>
                <div className="france-map-readout-dep">{hover.nom} <span>· {hover.code}</span></div>
                <div className="france-map-readout-val">
                  {hover.value === 0 ? 'Aucune cliente' : `${hover.value} cliente${hover.value > 1 ? 's' : ''}`}
                </div>
              </>
            ) : (
              <>
                <div className="france-map-readout-dep" style={{ color:'var(--ink-muted)' }}>France entière</div>
                <div className="france-map-readout-val">{total} clientes · {Object.keys(counts).length} départements</div>
              </>
            )}
          </div>
        </div>

        {/* Side: legend + top departments */}
        <div className="france-map-side">
          <div className="eyebrow" style={{ fontSize:'0.6rem', marginBottom: 10 }}>Échelle · clientes</div>
          <div className="france-legend">
            {legend.map(l => (
              <div key={l.label} className="france-legend-row">
                <span className="france-legend-swatch" style={{ background: l.c }} />
                <span className="france-legend-label">{l.label}</span>
              </div>
            ))}
          </div>

          <div className="eyebrow" style={{ fontSize:'0.6rem', margin: '22px 0 10px' }}>Départements en tête</div>
          <div className="france-top">
            {top.map((d, i) => (
              <div key={d.code} className="france-top-row">
                <span className="france-top-rank">{String(i + 1).padStart(2, '0')}</span>
                <span className="france-top-name">{d.nom}</span>
                <span className="france-top-code">{d.code}</span>
                <span className="france-top-val">{d.value}</span>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}

function Donut({ data }) {
  const total = data.reduce((s, x) => s + x.value, 0);
  let acc = 0;
  // Generate a harmonious palette: violet → gold via oklch-style HSL approximation
  const palette = [
    '#7b2fa0', '#a056d0', '#c9a84c', '#e8c96a',
    '#a8a8b8', '#8a6f28', '#5a3576', '#d4b870'
  ];
  const R = 86, R2 = 56, CX = 110, CY = 110;
  const arcs = data.map((d, i) => {
    const a0 = (acc / total) * Math.PI * 2 - Math.PI / 2;
    acc += d.value;
    const a1 = (acc / total) * Math.PI * 2 - Math.PI / 2;
    const large = (a1 - a0) > Math.PI ? 1 : 0;
    const x0 = CX + R * Math.cos(a0), y0 = CY + R * Math.sin(a0);
    const x1 = CX + R * Math.cos(a1), y1 = CY + R * Math.sin(a1);
    const x2 = CX + R2 * Math.cos(a1), y2 = CY + R2 * Math.sin(a1);
    const x3 = CX + R2 * Math.cos(a0), y3 = CY + R2 * Math.sin(a0);
    const path = `M ${x0} ${y0} A ${R} ${R} 0 ${large} 1 ${x1} ${y1} L ${x2} ${y2} A ${R2} ${R2} 0 ${large} 0 ${x3} ${y3} Z`;
    return <path key={i} d={path} fill={palette[i % palette.length]} stroke="var(--bg-card)" strokeWidth="2" />;
  });
  return (
    <div style={{ display:'flex', gap:20, alignItems:'center', marginTop: 8 }}>
      <svg className="donut" viewBox="0 0 220 220" style={{ flexShrink: 0 }}>
        {arcs}
        <circle cx={CX} cy={CY} r={R2 - 4} fill="none" stroke="rgba(201,168,76,0.3)" strokeWidth="1" />
        <circle cx={CX} cy={CY} r={R + 4} fill="none" stroke="rgba(201,168,76,0.3)" strokeWidth="1" />
        <text x={CX} y={CY - 2} textAnchor="middle" fill="var(--gold-2)" fontFamily="var(--font-display)" fontSize="22" letterSpacing="2">{total}</text>
        <text x={CX} y={CY + 16} textAnchor="middle" fill="var(--ink-muted)" fontFamily="var(--font-display)" fontSize="8" letterSpacing="3">CLIENTES</text>
      </svg>
      <div style={{ flex:1 }}>
        {data.map((d, i) => (
          <div key={d.name} style={{ display:'flex', alignItems:'center', gap:10, padding:'6px 0', borderBottom:'1px dashed rgba(201,168,76,0.15)' }}>
            <span style={{ width:14, height:14, background:palette[i % palette.length], boxShadow:`0 0 8px ${palette[i % palette.length]}`, flexShrink: 0 }} />
            <span style={{ flex:1, fontFamily:'var(--font-display)', fontSize:'0.78rem', letterSpacing:'0.1em' }}>{d.name}</span>
            <span style={{ fontFamily:'var(--font-mono)', fontSize:'0.85rem', color:'var(--gold-2)' }}>{d.value}</span>
          </div>
        ))}
      </div>
    </div>
  );
}

Object.assign(window, { EmployeHomePage, EmployeDetailPage, EmployeLivePage, EmployeCommentPage, EmployeStatsPage });
