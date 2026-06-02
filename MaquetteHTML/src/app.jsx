// PREDICT'IF — Main app & routing
const { useState: useStateA, useEffect: useEffectA, useMemo: useMemoA } = React;

const ROUTES = {
  // Public
  landing:        { name: 'Accueil',                kind: 'public' },
  signup:         { name: 'Inscription',            kind: 'public' },
  login:          { name: 'Connexion',              kind: 'public' },
  // Client
  'client-home':    { name: 'Accueil cliente',      kind: 'client' },
  'client-history': { name: 'Historique',           kind: 'client' },
  // Employé
  'employe-home':    { name: 'Accueil médium',         kind: 'employe' },
  'employe-detail':  { name: 'Détail séance',          kind: 'employe' },
  'employe-live':    { name: 'Consultation en cours',  kind: 'employe' },
  'employe-comment': { name: 'Commentaire',            kind: 'employe' },
  'employe-stats':   { name: 'Statistiques',           kind: 'employe' },
};

function App() {
  const [route, setRoute] = useStateA('landing');
  const [role, setRole]   = useStateA(null); // 'client' | 'employe' | null
  const [toasts, setToasts] = useStateA([]);
  const [consultations, setConsultations] = useStateA(window.PREDICTIF_DATA.consultations);

  // Synthetic assigned consult for the employee path
  const assigned = useMemoA(() => ({
    clientName: window.PREDICTIF_DATA.client.prenom + ' ' + window.PREDICTIF_DATA.client.nom,
    mediumName: 'Endora',
    mediumType: 'Cartomancien',
    requestedAt: 'il y a 7 min',
    scheduledAt: '22 h 30',
  }), []);

  const go = (r) => {
    setRoute(r);
    document.querySelector('.stage-scroll')?.scrollTo({ top: 0, behavior: 'instant' });
  };

  const pushToast = (t) => {
    const id = Math.random().toString(36).slice(2);
    setToasts(ts => [...ts, { ...t, id }]);
    setTimeout(() => setToasts(ts => ts.filter(x => x.id !== id)), 4500);
  };

  const addConsultation = (c) => setConsultations(cs => [c, ...cs]);

  const logout = () => {
    setRole(null);
    go('landing');
  };

  const screenLabel = ROUTES[route] ? ROUTES[route].name : 'Predict\'if';

  return (
    <div className={`app ${role ? 'is-auth' : ''}`} data-screen-label={screenLabel}>
      {/* — Slim header, only once a session is open — */}
      {role && (
        <header className="topbar">
          <button
            className="topbar-brand"
            onClick={() => go(role === 'client' ? 'client-home' : 'employe-home')}
            title="Retour à l'accueil"
          >
            <Sigil.pentagram style={{ width: 22, height: 22, color: 'var(--gold-2)', filter: 'drop-shadow(0 0 6px var(--gold-glow))' }} />
            <span className="wordmark">PREDICT<span className="apos">'</span>IF</span>
          </button>

          <div className="topbar-right">
            <span className="topbar-role">
              {role === 'client'
                ? `◆ ${window.PREDICTIF_DATA.client.prenom} ${window.PREDICTIF_DATA.client.nom}`
                : `◆ ${window.PREDICTIF_DATA.employe.prenom} ${window.PREDICTIF_DATA.employe.nom}`}
            </span>
            <button className="topbar-logout" onClick={logout}>
              Se déconnecter
            </button>
          </div>
        </header>
      )}

      {/* — Stage — */}
      <main className="stage">
        <div className="smoke" />
        <div className="stage-scroll" key={route}>
          {route === 'landing'        && <LandingPage go={go} />}
          {route === 'signup'         && <SignupPage go={go} pushToast={pushToast} />}
          {route === 'login'          && <LoginPage go={go} pushToast={pushToast} setRole={setRole} />}
          {route === 'client-home'    && <ClientHomePage go={go} pushToast={pushToast} addConsultation={addConsultation} />}
          {route === 'client-history' && <ClientHistoryPage go={go} consultations={consultations} />}
          {route === 'employe-home'    && <EmployeHomePage go={go} assigned={assigned} />}
          {route === 'employe-detail'  && <EmployeDetailPage go={go} assigned={assigned} pushToast={pushToast} />}
          {route === 'employe-live'    && <EmployeLivePage go={go} assigned={assigned} pushToast={pushToast} />}
          {route === 'employe-comment' && <EmployeCommentPage go={go} pushToast={pushToast} assigned={assigned} />}
          {route === 'employe-stats'   && <EmployeStatsPage go={go} />}
        </div>
      </main>

      {/* Toasts */}
      <div className="toast-deck">
        {toasts.map(t => <Toast key={t.id} title={t.title} msg={t.msg} error={t.error} />)}
      </div>
    </div>
  );
}

ReactDOM.createRoot(document.getElementById('root')).render(<App />);
