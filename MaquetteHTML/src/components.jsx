// PREDICT'IF — Shared visual components & glyphs
const { useState, useEffect, useRef, useMemo } = React;

// ————————————————————————————————————————————————————————
// SIGILS — handcrafted SVG glyphs (kept minimal, no figurative art)
// ————————————————————————————————————————————————————————
const Sigil = {
  pentagram: (p) => (
    <svg viewBox="0 0 100 100" fill="none" stroke="currentColor" strokeWidth="1.2" {...p}>
      <circle cx="50" cy="50" r="46" />
      <path d="M50 8 L59.7 36.7 L89.9 37 L65.7 55.1 L74.7 84 L50 66.5 L25.3 84 L34.3 55.1 L10.1 37 L40.3 36.7 Z" />
    </svg>
  ),
  moon: (p) => (
    <svg viewBox="0 0 100 100" fill="none" stroke="currentColor" strokeWidth="1.2" {...p}>
      <circle cx="50" cy="50" r="46" />
      <path d="M62 14 A36 36 0 1 0 62 86 A28 28 0 1 1 62 14 Z" fill="currentColor" fillOpacity="0.2" />
    </svg>
  ),
  sun: (p) => (
    <svg viewBox="0 0 100 100" fill="none" stroke="currentColor" strokeWidth="1.2" {...p}>
      <circle cx="50" cy="50" r="20" />
      {Array.from({ length: 12 }).map((_, i) => {
        const a = (i / 12) * Math.PI * 2;
        const x1 = 50 + Math.cos(a) * 28, y1 = 50 + Math.sin(a) * 28;
        const x2 = 50 + Math.cos(a) * (i % 2 ? 42 : 46), y2 = 50 + Math.sin(a) * (i % 2 ? 42 : 46);
        return <line key={i} x1={x1} y1={y1} x2={x2} y2={y2} />;
      })}
      <circle cx="50" cy="50" r="6" fill="currentColor" />
    </svg>
  ),
  eye: (p) => (
    <svg viewBox="0 0 100 100" fill="none" stroke="currentColor" strokeWidth="1.2" {...p}>
      <path d="M8 50 Q50 12, 92 50 Q50 88, 8 50 Z" />
      <circle cx="50" cy="50" r="14" />
      <circle cx="50" cy="50" r="5" fill="currentColor" />
      <line x1="50" y1="6" x2="50" y2="14" /><line x1="50" y1="86" x2="50" y2="94" />
    </svg>
  ),
  star: (p) => (
    <svg viewBox="0 0 100 100" fill="none" stroke="currentColor" strokeWidth="1.2" {...p}>
      <circle cx="50" cy="50" r="46" />
      <path d="M50 18 L55 45 L82 50 L55 55 L50 82 L45 55 L18 50 L45 45 Z" fill="currentColor" fillOpacity="0.15" />
      <circle cx="50" cy="50" r="2" fill="currentColor" />
    </svg>
  ),
  card: (p) => (
    <svg viewBox="0 0 100 100" fill="none" stroke="currentColor" strokeWidth="1.2" {...p}>
      <rect x="22" y="10" width="56" height="80" />
      <rect x="28" y="16" width="44" height="68" />
      <path d="M50 30 L56 50 L50 70 L44 50 Z" />
      <circle cx="50" cy="50" r="4" fill="currentColor" />
    </svg>
  ),
  // Ornament motifs
  diamond: (p) => (
    <svg viewBox="0 0 20 20" fill="none" stroke="currentColor" strokeWidth="1" {...p}>
      <path d="M10 2 L18 10 L10 18 L2 10 Z" />
      <circle cx="10" cy="10" r="2" fill="currentColor" />
    </svg>
  ),
  cross: (p) => (
    <svg viewBox="0 0 20 20" fill="none" stroke="currentColor" strokeWidth="1" {...p}>
      <path d="M10 2 L10 18 M2 10 L18 10" />
      <circle cx="10" cy="10" r="2.5" />
    </svg>
  ),
  // Corner ornament — gold filigree
  corner: (p) => (
    <svg viewBox="0 0 32 32" fill="none" stroke="currentColor" strokeWidth="1" {...p}>
      <path d="M2 2 L2 14" />
      <path d="M2 2 L14 2" />
      <path d="M2 2 Q12 2, 12 12" />
      <path d="M2 2 Q2 12, 12 12" />
      <circle cx="12" cy="12" r="1.6" fill="currentColor" />
      <path d="M14 14 L17 17" />
      <circle cx="19" cy="19" r="1" fill="currentColor" />
    </svg>
  ),
  // Astral row sigils
  zodiac: (p) => (
    <svg viewBox="0 0 64 64" fill="none" stroke="currentColor" strokeWidth="1.2" {...p}>
      <circle cx="32" cy="32" r="28" />
      <circle cx="32" cy="32" r="20" strokeDasharray="2 3" />
      {Array.from({length:12}).map((_,i)=>{const a=i*30*Math.PI/180;return <line key={i} x1={32+Math.cos(a)*20} y1={32+Math.sin(a)*20} x2={32+Math.cos(a)*28} y2={32+Math.sin(a)*28}/>})}
      <text x="32" y="38" textAnchor="middle" fill="currentColor" fontSize="14" stroke="none">♓</text>
    </svg>
  ),
  dragon: (p) => (
    <svg viewBox="0 0 64 64" fill="none" stroke="currentColor" strokeWidth="1.2" {...p}>
      <rect x="6" y="6" width="52" height="52" />
      <rect x="12" y="12" width="40" height="40" strokeDasharray="2 2" />
      <text x="32" y="40" textAnchor="middle" fill="currentColor" fontSize="22" stroke="none" fontFamily="serif">兎</text>
    </svg>
  ),
  feather: (p) => (
    <svg viewBox="0 0 64 64" fill="none" stroke="currentColor" strokeWidth="1.2" {...p}>
      <circle cx="32" cy="32" r="28" />
      <path d="M32 12 L32 52 M22 22 L32 28 L42 22 M22 32 L32 36 L42 32 M22 42 L32 46 L42 42" />
    </svg>
  ),
  totem: (p) => (
    <svg viewBox="0 0 64 64" fill="none" stroke="currentColor" strokeWidth="1.2" {...p}>
      <circle cx="32" cy="32" r="28" />
      <circle cx="24" cy="26" r="4" />
      <circle cx="40" cy="26" r="4" />
      <circle cx="24" cy="26" r="1.5" fill="currentColor" />
      <circle cx="40" cy="26" r="1.5" fill="currentColor" />
      <path d="M26 38 Q32 44, 38 38" />
      <path d="M14 20 L20 24 M50 20 L44 24" />
    </svg>
  ),
  lock: (p) => (
    <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.4" {...p}>
      <rect x="5" y="11" width="14" height="10" />
      <path d="M8 11 V7 a4 4 0 0 1 8 0 V11" />
      <circle cx="12" cy="16" r="1.4" fill="currentColor" />
    </svg>
  ),
  check: (p) => (
    <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.6" {...p}>
      <circle cx="12" cy="12" r="10" />
      <path d="M7 12 L11 16 L17 8" />
    </svg>
  ),
  bell: (p) => (
    <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.4" {...p}>
      <path d="M5 18 L19 18 L17 15 V11 a5 5 0 0 0 -10 0 V15 Z" />
      <path d="M10 21 a2 2 0 0 0 4 0" />
      <circle cx="12" cy="5" r="1.5" fill="currentColor" />
    </svg>
  ),
  scroll: (p) => (
    <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.4" {...p}>
      <path d="M5 4 H17 a2 2 0 0 1 2 2 V18 a2 2 0 0 0 2 2 H7 a2 2 0 0 1 -2 -2 V4 a2 2 0 0 0 -2 0 V18" />
      <line x1="9" y1="9" x2="15" y2="9" />
      <line x1="9" y1="13" x2="15" y2="13" />
    </svg>
  ),
  crystal: (p) => (
    <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.4" {...p}>
      <circle cx="12" cy="13" r="6" />
      <path d="M7 19 L17 19" />
      <path d="M9 13 a3 3 0 0 1 3 -3" />
    </svg>
  ),
  flame: (p) => (
    <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.4" {...p}>
      <path d="M12 3 Q15 9, 17 12 a5 5 0 0 1 -10 0 Q9 9, 12 3 Z" />
      <path d="M10 14 Q12 11, 14 14 a2 2 0 0 1 -4 0 Z" fill="currentColor" fillOpacity="0.3" />
    </svg>
  ),
  arrow: (p) => (
    <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5" {...p}>
      <path d="M5 12 L19 12 M14 7 L19 12 L14 17" />
    </svg>
  ),
  pause: (p) => (
    <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5" {...p}>
      <rect x="6" y="5" width="4" height="14" />
      <rect x="14" y="5" width="4" height="14" />
    </svg>
  ),
  play: (p) => (
    <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5" {...p}>
      <path d="M7 4 L20 12 L7 20 Z" />
    </svg>
  ),
  chart: (p) => (
    <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.4" {...p}>
      <path d="M4 20 L4 4" /><path d="M4 20 L20 20" />
      <path d="M7 17 L11 12 L14 14 L19 8" />
    </svg>
  )
};

// ————————————————————————————————————————————————————————
// PARTICLES — drifting gold/violet motes
// ————————————————————————————————————————————————————————
function Particles() {
  const canvasRef = useRef(null);
  useEffect(() => {
    const c = canvasRef.current;
    const ctx = c.getContext('2d');
    let raf;
    const resize = () => {
      const r = c.parentElement.getBoundingClientRect();
      c.width = r.width * devicePixelRatio;
      c.height = r.height * devicePixelRatio;
      c.style.width = r.width + 'px';
      c.style.height = r.height + 'px';
    };
    resize();
    window.addEventListener('resize', resize);
    const N = 38;
    const motes = Array.from({ length: N }, () => ({
      x: Math.random() * c.width,
      y: Math.random() * c.height,
      r: Math.random() * 1.6 + 0.4,
      vy: -(Math.random() * 0.3 + 0.1),
      vx: (Math.random() - 0.5) * 0.15,
      a: Math.random() * 0.6 + 0.2,
      hue: Math.random() < 0.6 ? 'gold' : 'violet',
      tw: Math.random() * Math.PI * 2,
    }));
    const tick = () => {
      ctx.clearRect(0, 0, c.width, c.height);
      motes.forEach(m => {
        m.y += m.vy * devicePixelRatio;
        m.x += m.vx * devicePixelRatio;
        m.tw += 0.04;
        if (m.y < -10) { m.y = c.height + 10; m.x = Math.random() * c.width; }
        const a = m.a * (0.6 + 0.4 * Math.sin(m.tw));
        ctx.beginPath();
        ctx.arc(m.x, m.y, m.r * devicePixelRatio, 0, Math.PI * 2);
        ctx.fillStyle = m.hue === 'gold'
          ? `rgba(232, 201, 106, ${a})`
          : `rgba(160, 86, 208, ${a * 0.8})`;
        ctx.shadowColor = ctx.fillStyle;
        ctx.shadowBlur = 8 * devicePixelRatio;
        ctx.fill();
      });
      raf = requestAnimationFrame(tick);
    };
    tick();
    return () => { cancelAnimationFrame(raf); window.removeEventListener('resize', resize); };
  }, []);
  return <canvas ref={canvasRef} className="particles" />;
}

// ————————————————————————————————————————————————————————
// TAROT CARD
// ————————————————————————————————————————————————————————
function TarotCard({ medium, onChoose, locked, available = true }) {
  const Glyph = Sigil[medium.glyph] || Sigil.moon;
  return (
    <div className={`tarot ${locked ? 'locked-anchor' : ''}`}>
      <Sigil.corner className="corner tl" />
      <Sigil.corner className="corner tr" />
      <Sigil.corner className="corner bl" />
      <Sigil.corner className="corner br" />
      <div className="tarot-rank">{medium.rank}</div>
      <div className="tarot-portrait">
        <Sigil.moon className="moon-bg" />
        <Glyph className="glyph-large" />
      </div>
      <div className="tarot-name">{medium.denomination}</div>
      <div className="tarot-divider">
        <Sigil.diamond />
      </div>
      <div className="tarot-meta">
        {medium.type} · {medium.genre === 'F' ? 'Voix féminine' : medium.genre === 'H' ? 'Voix masculine' : 'Voix neutre'}
      </div>
      <div className="tarot-body">{medium.presentation}</div>
      <div className="tarot-stats">
        <div className="stat">
          <span>{medium.detail.label}</span>
          <strong style={{ fontSize: '0.85rem' }}>{medium.detail.value}</strong>
        </div>
      </div>
      {!locked && (
        <button className="btn btn-ghost" style={{ width: '100%', justifyContent: 'center' }} onClick={() => onChoose && onChoose(medium)} disabled={!available}>
          {available ? 'Demander une consultation' : 'Indisponible'}
        </button>
      )}
    </div>
  );
}

// ————————————————————————————————————————————————————————
// PAGE HEADING — ornamental
// ————————————————————————————————————————————————————————
function PageHeading({ eyebrow, title, sub }) {
  return (
    <div className="page-heading">
      <div className="ornament">
        <span className="rule" />
        <Sigil.diamond style={{ width: 14, height: 14 }} />
        <span>{eyebrow}</span>
        <Sigil.diamond style={{ width: 14, height: 14 }} />
        <span className="rule" />
      </div>
      <h1>{title}</h1>
      {sub && <p className="body-text" style={{ maxWidth: 640, margin: '0 auto', color: 'var(--ink-muted)', fontStyle: 'italic' }}>{sub}</p>}
    </div>
  );
}

// ————————————————————————————————————————————————————————
// TOAST
// ————————————————————————————————————————————————————————
function Toast({ title, msg, error, sms, mediumName, mediumPhone }) {
  if (sms) {
    return (
      <div className="toast toast-sms">
        <div className="sms-head">
          <Sigil.bell className="glyph" />
          <div>
            <div className="title">{title}</div>
            <div style={{ fontFamily:'var(--font-mono)', fontSize:'0.66rem', color:'var(--ink-faint)', letterSpacing:'0.05em', marginTop:2 }}>
              SMS · maintenant · expéditeur PREDICT'IF
            </div>
          </div>
        </div>
        <div className="sms-body">
          <span style={{ color:'var(--ink-bone)' }}>{mediumName}</span> vous attend.<br/>
          Composez le <strong className="sms-phone">{mediumPhone}</strong> lorsque vous êtes prête.
        </div>
      </div>
    );
  }
  return (
    <div className={`toast ${error ? 'error' : ''}`}>
      {error ? <Sigil.flame className="glyph" /> : <Sigil.check className="glyph" />}
      <div>
        <div className="title">{title}</div>
        <div className="msg">{msg}</div>
      </div>
    </div>
  );
}

// Export to window so other scripts can use
Object.assign(window, { Sigil, Particles, TarotCard, PageHeading, Toast });
