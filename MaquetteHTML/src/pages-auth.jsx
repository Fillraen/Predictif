// PREDICT'IF — Pages 1-3: Landing, Signup, Login (v2)
const { useState: useStateP1 } = React;

// ————————————————————————————————————————————————————————
// PAGE 1 — Landing (non-connecté)
// ————————————————————————————————————————————————————————
function LandingPage({ go }) {
  const data = window.PREDICTIF_DATA;
  const previewMediums = data.mediums.slice(0, 3);

  return (
    <div className="page" style={{ padding: 0 }}>
      <Particles />
      <div className="hero">
        <div className="orb" style={{ width: 360, height: 360, top: '-80px', left: '-60px', background: 'rgba(123,47,160,0.35)' }} />
        <div className="orb" style={{ width: 320, height: 320, bottom: '-80px', right: '0px', background: 'rgba(201,168,76,0.18)', animationDelay: '-3s' }} />

        <Sigil.pentagram className="hero-symbol" />
        <div className="ornament" style={{ display:'flex', alignItems:'center', justifyContent:'center', gap:18, color:'var(--gold-1)', fontSize:'0.7rem', letterSpacing:'0.5em', fontFamily:'var(--font-display)', textTransform:'uppercase' }}>
          <span style={{ width:60, height:1, background:'linear-gradient(90deg, transparent, var(--gold-1), transparent)' }} />
          <span>Anno · MMXXVI</span>
          <span style={{ width:60, height:1, background:'linear-gradient(90deg, transparent, var(--gold-1), transparent)' }} />
        </div>
        <h1 className="hero-mark">PREDICT<span className="apos">'</span><span className="if">IF</span></h1>
        <p className="hero-tagline">
          Cabinet de voyance en ligne. Six médiums, trois disciplines, une seule lumière — celle que vous choisirez d'allumer.
        </p>
        <div className="hero-cta">
          <button className="btn btn-primary" onClick={() => go('signup')}>
            S'inscrire
            <Sigil.arrow style={{ width: 18, height: 18 }} />
          </button>
          <button className="btn btn-ghost" onClick={() => go('login')}>Se connecter</button>
        </div>
      </div>

      <div style={{ padding: '40px 72px 100px', position: 'relative' }}>
        <div className="section-head">
          <div className="left">
            <span className="eyebrow">Les six voix · Aperçu</span>
            <h2>Médiums du cabinet</h2>
          </div>
          <p style={{ maxWidth: 380, color: 'var(--ink-muted)', fontStyle: 'italic', textAlign: 'right' }}>
            Une présentation détaillée de chaque médium n'est accessible qu'aux initiés. Inscrivez-vous pour franchir le seuil.
          </p>
        </div>
        <div className="grid-3 grid-mediums-preview">
          {previewMediums.map((m) => (
            <TarotCard key={m.id} medium={m} locked />
          ))}
        </div>

        <div className="section-head" style={{ marginTop: 80 }}>
          <div className="left">
            <span className="eyebrow">Votre profil · Scellé</span>
            <h2>Votre carte personnelle</h2>
          </div>
        </div>
        <div style={{ position: 'relative' }}>
          <div className="locked">
            <AstralProfileCard />
          </div>
          <div className="lock-veil">
            <Sigil.lock className="padlock" />
            <div className="eyebrow">Profil scellé</div>
            <p style={{ maxWidth: 380, textAlign: 'center', color: 'var(--ink-parchment)', fontStyle: 'italic' }}>
              Inscrivez-vous : le service <span style={{ color:'var(--gold-2)', fontFamily:'var(--font-mono)' }}>IfAstroNet</span> révélera votre signe zodiaque,
              votre signe chinois, votre couleur porte-bonheur et votre animal totem.
            </p>
            <button className="btn btn-primary" style={{ marginTop: 10 }} onClick={() => go('signup')}>
              Briser le sceau
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

// Astral profile card — shared
function AstralProfileCard({ realData, calculating }) {
  const data = window.PREDICTIF_DATA;
  const p = realData ? data.client.profil : data.client.profil;
  const c = realData ? data.client : { prenom: '████████', nom: '████' };
  return (
    <div className="astral">
      <Sigil.corner className="corner tl" />
      <Sigil.corner className="corner tr" />
      <Sigil.corner className="corner bl" />
      <Sigil.corner className="corner br" />
      <div style={{ textAlign: 'center' }}>
        <div className="eyebrow">Calculé par IfAstroNet · Carte natale</div>
        <h2 style={{ marginTop: 8, letterSpacing: '0.1em' }}>{c.prenom} {c.nom}</h2>
        <div style={{ display:'flex', alignItems:'center', justifyContent:'center', gap:14, marginTop:8, color:'var(--gold-1)' }}>
          <span style={{ width:60, height:1, background:'linear-gradient(90deg, transparent, var(--gold-1))' }} />
          <Sigil.diamond style={{ width:12, height:12 }} />
          <span style={{ fontFamily:'var(--font-body)', fontStyle:'italic', color:'var(--ink-muted)' }}>{realData ? data.client.naissance : '00 mois 0000'}</span>
          <Sigil.diamond style={{ width:12, height:12 }} />
          <span style={{ width:60, height:1, background:'linear-gradient(90deg, var(--gold-1), transparent)' }} />
        </div>
      </div>
      <div className="astral-grid">
        <div className="astral-row">
          <Sigil.zodiac className="sigil" />
          <div>
            <div className="label">Signe zodiaque</div>
            <div className="value">{p.zodiaque} {realData && <span style={{color:'var(--gold-2)', marginLeft:8}}>{p.zodiaqueGlyph}</span>}</div>
          </div>
        </div>
        <div className="astral-row">
          <Sigil.dragon className="sigil" />
          <div>
            <div className="label">Signe chinois</div>
            <div className="value">{p.chinois}</div>
          </div>
        </div>
        <div className="astral-row">
          <Sigil.feather className="sigil" />
          <div>
            <div className="label">Couleur porte-bonheur</div>
            <div className="value">
              <span className="swatch" style={{ background: p.couleurHex, color: p.couleurHex }} />
              {p.couleur}
            </div>
          </div>
        </div>
        <div className="astral-row">
          <Sigil.totem className="sigil" />
          <div>
            <div className="label">Animal totem</div>
            <div className="value">{p.animal}</div>
          </div>
        </div>
      </div>
    </div>
  );
}

// ————————————————————————————————————————————————————————
// PAGE 2 — Inscription
// ————————————————————————————————————————————————————————
function SignupPage({ go, pushToast }) {
  const departements = window.PREDICTIF_DATA.departements;
  const [form, setForm] = useStateP1({
    prenom: 'Eliane', nom: 'Morvan', naissance: '1987-03-14',
    numero: '23', voie: 'rue des Carmélites', ville: 'Paris', departement: '75',
    telephone: '+33 6 71 22 84 19', email: 'eliane.morvan@gmail.com', password: ''
  });
  const [error, setError] = useStateP1('');
  // mailState: null | { kind: 'success' | 'failure' }
  const [mailState, setMailState] = useStateP1(null);
  const [submitting, setSubmitting] = useStateP1(false);

  const update = (k, v) => setForm(f => ({ ...f, [k]: v }));

  const submit = (e) => {
    e.preventDefault();
    // Validation
    const missing = ['prenom','nom','naissance','numero','voie','ville','departement','telephone','email','password'].filter(k => !form[k]);
    if (missing.length) { setError('Tous les champs sont requis. Le sceau exige le détail.'); return; }
    if (form.password.length < 4) { setError('Une formule de passe digne d\'un initié compte au moins quatre signes.'); return; }
    // Simulate failure if email contains "fail" — otherwise success
    const fail = /fail|@bloque|@interdit/i.test(form.email);
    setError('');
    setSubmitting(true);
    setTimeout(() => {
      setSubmitting(false);
      setMailState({ kind: fail ? 'failure' : 'success' });
    }, 700);
  };

  if (mailState) {
    return <MailEnvelope kind={mailState.kind} form={form} onContinue={() => go('login')} onRetry={() => setMailState(null)} />;
  }

  return (
    <div className="auth-shell">
      <Particles />
      <div className="orb" style={{ width: 400, height: 400, top: '-100px', left: '20%', background: 'rgba(123,47,160,0.30)' }} />
      <div className="orb" style={{ width: 320, height: 320, bottom: '-80px', right: '10%', background: 'rgba(201,168,76,0.15)', animationDelay: '-2s' }} />

      <div className="auth-card" style={{ width: 580, position: 'relative', zIndex: 2 }}>
        <Sigil.corner className="corner tl" />
        <Sigil.corner className="corner tr" />
        <Sigil.corner className="corner bl" />
        <Sigil.corner className="corner br" />
        <div style={{ textAlign: 'center', marginBottom: 28 }}>
          <Sigil.pentagram style={{ width: 44, height: 44, color: 'var(--gold-2)', filter: 'drop-shadow(0 0 12px var(--gold-glow))' }} />
          <div className="eyebrow" style={{ marginTop: 8 }}>Rite d'admission</div>
          <h2 style={{ marginTop: 8, fontSize: '1.7rem', letterSpacing: '0.1em' }}>Rejoindre les mystères</h2>
          <p style={{ color:'var(--ink-muted)', fontStyle:'italic', marginTop:8, fontSize:'0.92rem' }}>
            Le service IfAstroNet calculera votre profil dès le sceau apposé.
          </p>
        </div>
        <form onSubmit={submit}>
          <div className="row-2">
            <div className="field"><label>Prénom</label><input value={form.prenom} onChange={e=>update('prenom',e.target.value)} /></div>
            <div className="field"><label>Nom</label><input value={form.nom} onChange={e=>update('nom',e.target.value)} /></div>
          </div>
          <div className="row-2">
            <div className="field"><label>Date de naissance</label><input type="date" value={form.naissance} onChange={e=>update('naissance',e.target.value)} /></div>
            <div className="field"><label>Téléphone</label><input value={form.telephone} onChange={e=>update('telephone',e.target.value)} /></div>
          </div>
          <div className="field-group">
            <div className="field-group-head">
              <label>Adresse postale</label>
              <span className="field-fixed">◆ France</span>
            </div>
            <div className="row-num-voie">
              <div className="field"><label>N°</label><input value={form.numero} onChange={e=>update('numero',e.target.value)} placeholder="23" /></div>
              <div className="field"><label>Nom de voie</label><input value={form.voie} onChange={e=>update('voie',e.target.value)} placeholder="rue des Carmélites" /></div>
            </div>
            <div className="row-2">
              <div className="field"><label>Ville</label><input value={form.ville} onChange={e=>update('ville',e.target.value)} placeholder="Paris" /></div>
              <div className="field">
                <label>Département</label>
                <select value={form.departement} onChange={e=>update('departement',e.target.value)}>
                  <option value="">— Choisir —</option>
                  {departements.map(d => (
                    <option key={d.code} value={d.code}>{d.code} · {d.nom}</option>
                  ))}
                </select>
              </div>
            </div>
          </div>
          <div className="field"><label>Adresse électronique</label><input type="email" value={form.email} onChange={e=>update('email',e.target.value)} placeholder="vous@maison.tld" /></div>
          <div className="field"><label>Formule de passe</label><input type="password" value={form.password} onChange={e=>update('password',e.target.value)} placeholder="·······" /></div>

          {error && (
            <div style={{ background:'rgba(138, 31, 42, 0.15)', border:'1px solid rgba(212, 74, 85, 0.4)', padding:'10px 14px', color:'#e89aa3', fontStyle:'italic', fontSize:'0.92rem', marginBottom:18 }}>
              <Sigil.flame style={{ width:14, height:14, verticalAlign:-2, marginRight:8, color:'#d44a55' }} />
              {error}
            </div>
          )}

          <button type="submit" className="btn btn-primary" style={{ width: '100%', justifyContent: 'center', marginTop: 8 }} disabled={submitting}>
            {submitting ? (<><span className="spinner" style={{ width:14, height:14 }} /> Apposition du sceau…</>) : (<>Rejoindre les mystères <Sigil.arrow style={{ width: 16, height: 16 }} /></>)}
          </button>

          <div style={{ fontSize:'0.75rem', color:'var(--ink-muted)', fontStyle:'italic', marginTop:12, textAlign:'center' }}>
            Astuce démo : un email contenant <code style={{ color:'var(--gold-2)' }}>fail</code> simulera un échec.
          </div>
        </form>
        <div style={{ textAlign:'center', marginTop:24, color:'var(--ink-muted)', fontSize:'0.92rem' }}>
          Déjà initié(e) ? <a onClick={()=>go('login')} style={{cursor:'pointer'}}>Ouvrir le portail</a>
        </div>
      </div>
    </div>
  );
}

// — Mail simulation overlay (envelope/parchment) —
function MailEnvelope({ kind, form, onContinue, onRetry }) {
  const success = kind === 'success';
  return (
    <div className="auth-shell">
      <Particles />
      <div className="orb" style={{ width: 480, height: 480, top: '0%', left: '20%', background: success ? 'rgba(201,168,76,0.22)' : 'rgba(138,31,42,0.25)' }} />

      <div style={{
        width: 660, maxWidth: '95%', position:'relative', zIndex:2,
        background:'linear-gradient(180deg, #2a2218, #1a1410)',
        border: success ? '1px solid rgba(232, 201, 106, 0.5)' : '1px solid rgba(212, 74, 85, 0.5)',
        padding: '36px 44px 38px',
        boxShadow: success ? '0 30px 80px -20px rgba(201, 168, 76, 0.3)' : '0 30px 80px -20px rgba(138, 31, 42, 0.3)',
        animation: 'page-in 0.5s var(--tx)'
      }}>
        <Sigil.corner className="corner" style={{ position:'absolute', top:14, left:14, width:28, height:28, color: success ? 'var(--gold-1)' : '#d44a55' }} />
        <Sigil.corner className="corner" style={{ position:'absolute', top:14, right:14, width:28, height:28, color: success ? 'var(--gold-1)' : '#d44a55', transform:'scaleX(-1)' }} />
        <Sigil.corner className="corner" style={{ position:'absolute', bottom:14, left:14, width:28, height:28, color: success ? 'var(--gold-1)' : '#d44a55', transform:'scaleY(-1)' }} />
        <Sigil.corner className="corner" style={{ position:'absolute', bottom:14, right:14, width:28, height:28, color: success ? 'var(--gold-1)' : '#d44a55', transform:'scale(-1,-1)' }} />

        {/* Wax seal */}
        <div style={{
          position:'absolute', top:-28, left:'50%', transform:'translateX(-50%)',
          width:60, height:60, borderRadius:'50%',
          background: success ? 'radial-gradient(circle at 35% 30%, #d4a02e, #5c3812)' : 'radial-gradient(circle at 35% 30%, #b53a48, #4a151c)',
          display:'flex', alignItems:'center', justifyContent:'center',
          boxShadow:'0 4px 14px rgba(0,0,0,0.6), inset 0 -3px 4px rgba(0,0,0,0.4)'
        }}>
          <Sigil.pentagram style={{ width: 30, height: 30, color: success ? '#ffe5a0' : '#ffcfd4', opacity: 0.85 }} />
        </div>

        {/* Mail header */}
        <div style={{ display:'flex', justifyContent:'space-between', alignItems:'flex-start', marginTop:16, marginBottom:20, paddingBottom:18, borderBottom:'1px dashed rgba(201, 168, 76, 0.3)' }}>
          <div>
            <div className="eyebrow" style={{ color: success ? 'var(--gold-2)' : '#e89aa3' }}>
              {success ? 'Mail · Confirmation' : 'Mail · Échec'}
            </div>
            <div style={{ fontFamily:'var(--font-mono)', fontSize:'0.82rem', color:'var(--ink-muted)', marginTop:10 }}>
              <div><span style={{ color:'var(--gold-deep)' }}>De </span> contact@predict.if</div>
              <div><span style={{ color:'var(--gold-deep)' }}>À  </span> {form.email}</div>
              <div><span style={{ color:'var(--gold-deep)' }}>Suj</span> {success ? "Bienvenue chez PREDICT'IF" : "Inscription rejetée — PREDICT'IF"}</div>
              <div><span style={{ color:'var(--gold-deep)' }}>Le </span> 24 mai 2026, 22h17</div>
            </div>
          </div>
          <div style={{ textAlign:'right' }}>
            <div style={{ fontFamily:'var(--font-display)', letterSpacing:'0.3em', color:'var(--ink-bone)', fontSize:'0.85rem' }}>PREDICT<span style={{color:'var(--gold-2)'}}>'</span>IF</div>
            <div style={{ fontFamily:'var(--font-body)', fontStyle:'italic', fontSize:'0.72rem', color:'var(--ink-muted)', marginTop:2 }}>Le cabinet des voix</div>
          </div>
        </div>

        {/* Mail body */}
        {success ? (
          <div>
            <h2 style={{ fontSize:'1.7rem', letterSpacing:'0.05em', marginBottom:14 }}>Bienvenue, {form.prenom}.</h2>
            <p style={{ fontStyle:'italic', color:'var(--ink-bone)', lineHeight:1.6, fontSize:'1.05rem' }}>
              Votre demande d'admission au cabinet PREDICT'IF a été acceptée.
              Le service <span style={{ color:'var(--gold-2)', fontFamily:'var(--font-mono)' }}>IfAstroNet</span> a calculé votre profil astral à partir des éléments transmis.
            </p>
            <p style={{ color:'var(--ink-parchment)', lineHeight:1.6, marginTop:14 }}>
              Vos lames sont prêtes. Six voix vous attendent au seuil. Ouvrez le portail dès que la nuit sera douce.
            </p>
            <div style={{ marginTop:24, padding:'16px 20px', background:'rgba(201, 168, 76, 0.06)', borderLeft:'2px solid var(--gold-1)' }}>
              <div className="eyebrow" style={{ fontSize:'0.6rem' }}>Détails de l'inscription</div>
              <div style={{ fontFamily:'var(--font-mono)', fontSize:'0.82rem', color:'var(--ink-parchment)', marginTop:6 }}>
                Identifiant · {form.email}<br/>
                Téléphone   · {form.telephone}<br/>
                Sceau émis  · 24 mai 2026 · cabinet du Marais
              </div>
            </div>
            <div style={{ marginTop:24, fontFamily:'var(--font-body)', fontStyle:'italic', color:'var(--ink-muted)' }}>
              Que les présages vous soient favorables,<br/>
              <span style={{ fontFamily:'var(--font-display)', fontSize:'1.1rem', color:'var(--ink-bone)', fontStyle:'normal' }}>Le bureau d'admission</span>
            </div>
            <button className="btn btn-primary" style={{ width:'100%', justifyContent:'center', marginTop:28 }} onClick={onContinue}>
              Ouvrir le portail
              <Sigil.arrow style={{ width:16, height:16 }} />
            </button>
          </div>
        ) : (
          <div>
            <h2 style={{ fontSize:'1.5rem', letterSpacing:'0.05em', marginBottom:14, color:'#e89aa3' }}>L'admission n'a pu être prononcée.</h2>
            <p style={{ fontStyle:'italic', color:'var(--ink-parchment)', lineHeight:1.6 }}>
              Le cabinet PREDICT'IF n'a pu apposer son sceau sur votre demande.
              Le service <span style={{ color:'var(--gold-2)', fontFamily:'var(--font-mono)' }}>IfAstroNet</span> a refusé le calcul du profil — un élément de la requête est resté obscur.
            </p>
            <div style={{ marginTop:18, padding:'16px 20px', background:'rgba(138, 31, 42, 0.12)', borderLeft:'2px solid #d44a55' }}>
              <div className="eyebrow" style={{ fontSize:'0.6rem', color:'#e89aa3' }}>Cause invoquée</div>
              <div style={{ fontFamily:'var(--font-body)', color:'#f1bdc3', marginTop:6, fontStyle:'italic' }}>
                « Adresse électronique en discorde avec les registres du cabinet. »
              </div>
            </div>
            <div style={{ marginTop:24, fontFamily:'var(--font-body)', fontStyle:'italic', color:'var(--ink-muted)' }}>
              Vous pouvez reformuler votre demande quand la lune le permettra.
            </div>
            <div style={{ display:'flex', gap:12, marginTop:28 }}>
              <button className="btn btn-ghost" style={{ flex:1, justifyContent:'center' }} onClick={onRetry}>Reformuler la demande</button>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}

// ————————————————————————————————————————————————————————
// PAGE 3 — Connexion (rôle déterminé par l'email)
// ————————————————————————————————————————————————————————
function LoginPage({ go, pushToast, setRole }) {
  const data = window.PREDICTIF_DATA;
  const [email, setEmail] = useStateP1('');
  const [pwd, setPwd] = useStateP1('');
  const [err, setErr] = useStateP1('');

  // Determine role from email
  const resolveRole = (mail) => {
    const m = (mail || '').toLowerCase().trim();
    if (data.employes.find(e => e.email.toLowerCase() === m)) return 'employe';
    if (data.clients.find(c => c.email.toLowerCase() === m)) return 'client';
    return null;
  };

  const submit = (e) => {
    e.preventDefault();
    if (!email || !pwd) { setErr('Le portail ne s\'ouvre qu\'avec un nom et une formule.'); return; }
    const r = resolveRole(email);
    if (!r) { setErr('Cet email est inconnu du cabinet. Vérifiez votre formule ou inscrivez-vous.'); return; }
    setRole(r);
    pushToast({
      title: 'Portail ouvert',
      msg: r === 'client' ? 'Bienvenue, Eliane. Vos lames vous attendent.' : 'Bienvenue, Cassandre. Une consultation vous est assignée.'
    });
    setTimeout(() => go(r === 'client' ? 'client-home' : 'employe-home'), 700);
  };

  return (
    <div className="auth-shell">
      <Particles />
      <div className="orb" style={{ width: 360, height: 360, top: '10%', right: '-80px', background: 'rgba(123,47,160,0.30)' }} />
      <div className="orb" style={{ width: 280, height: 280, bottom: '5%', left: '-40px', background: 'rgba(201,168,76,0.15)', animationDelay: '-2s' }} />

      <div className="auth-card" style={{ position: 'relative', zIndex: 2 }}>
        <Sigil.corner className="corner tl" />
        <Sigil.corner className="corner tr" />
        <Sigil.corner className="corner bl" />
        <Sigil.corner className="corner br" />
        <div style={{ textAlign: 'center', marginBottom: 28 }}>
          <Sigil.eye style={{ width: 48, height: 48, color: 'var(--gold-2)', filter: 'drop-shadow(0 0 12px var(--gold-glow))' }} />
          <div className="eyebrow" style={{ marginTop: 8 }}>Seuil du cabinet</div>
          <h2 style={{ marginTop: 8, fontSize: '1.7rem', letterSpacing: '0.1em' }}>Ouvrir le portail</h2>
        </div>
        <form onSubmit={submit}>
          <div className="field">
            <label>Adresse électronique</label>
            <input type="email" value={email} onChange={e=>{setEmail(e.target.value); setErr('');}} />
          </div>
          <div className="field" style={{ marginBottom: 18 }}><label>Formule de passe</label><input type="password" value={pwd} onChange={e=>setPwd(e.target.value)} /></div>

          {err && (
            <div style={{ background:'rgba(138, 31, 42, 0.15)', border:'1px solid rgba(212, 74, 85, 0.4)', padding:'10px 14px', color:'#e89aa3', fontStyle:'italic', fontSize:'0.92rem', marginBottom:18 }}>
              <Sigil.flame style={{ width:14, height:14, verticalAlign:-2, marginRight:8, color:'#d44a55' }} />
              {err}
            </div>
          )}

          <button type="submit" className="btn btn-primary" style={{ width: '100%', justifyContent: 'center', marginTop: 8 }}>
            Ouvrir le portail
            <Sigil.arrow style={{ width: 16, height: 16 }} />
          </button>
        </form>
        <div style={{ textAlign:'center', marginTop:24, color:'var(--ink-muted)', fontSize:'0.92rem' }}>
          Pas encore initié(e) ? <a onClick={()=>go('signup')} style={{cursor:'pointer'}}>S'inscrire</a>
        </div>
      </div>
    </div>
  );
}

Object.assign(window, { LandingPage, SignupPage, LoginPage, AstralProfileCard });
