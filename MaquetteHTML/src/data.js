// PREDICT'IF — Mock data (v2 — règles métier mises à jour)
window.PREDICTIF_DATA = {
  client: {
    prenom: 'Eliane',
    nom: 'Morvan',
    email: 'eliane.morvan@gmail.com',
    naissance: '14 mars 1987',
    adresse: {
      numero: '23',
      voie: 'rue des Carmélites',
      ville: 'Paris',
      departement: '75', // Paris — code département (résolu via PREDICTIF_DATA.departements)
      pays: 'France',     // forcément la France
    },
    telephone: '+33 6 71 22 84 19',
    profil: {
      zodiaque: 'Poissons',
      zodiaqueGlyph: '♓',
      chinois: 'Lapin de Feu',
      chinoisGlyph: '兎',
      couleur: 'Mauve cendré',
      couleurHex: '#9e6fb3',
      animal: 'Hibou des marais',
    },
  },
  employe: {
    prenom: 'Cassandre',
    nom: 'Lerieux',
    email: 'cassandre.lerieux@predict.if',
    genre: 'F',
    disponible: true,
    enConsultation: false,
    consultationsCount: 28,
  },
  // 6 médiums imposés par le brief
  mediums: [
    {
      id: 'gwenaelle',
      denomination: 'Gwenaëlle',
      rank: 'XVIII · La Lune',
      type: 'Spirite',
      genre: 'F',
      telephone: '+33 6 47 18 22 09',
      presentation: 'Établit le contact dans les heures liminaires. Ne théâtralise jamais ce que l\'au-delà murmure.',
      detail: { label: 'Support rituel', value: 'Boule de cristal de Bohème' },
      glyph: 'crystal',
    },
    {
      id: 'tran',
      denomination: 'Professeur Tran',
      rank: 'IX · L\'Ermite',
      type: 'Spirite',
      genre: 'H',
      telephone: '+33 6 12 90 44 71',
      presentation: 'Méthodes plurielles, jamais dogmatique. Une voix qui sait s\'effacer pour laisser parler les signes.',
      detail: { label: 'Supports', value: 'Marc de café · Boule de cristal · Oreilles de lapin' },
      glyph: 'eye',
    },
    {
      id: 'irma',
      denomination: 'Mme Irma',
      rank: 'I · Le Bateleur',
      type: 'Cartomancien',
      genre: 'F',
      telephone: '+33 6 88 03 55 12',
      presentation: 'Tirage en croix celtique. Une lame, jamais deux. Lit à la lueur d\'une seule bougie.',
      detail: { label: 'Jeu rituel', value: 'Tarot de Marseille, édition 1751' },
      glyph: 'card',
    },
    {
      id: 'endora',
      denomination: 'Endora',
      rank: 'XIII · La Lame sans nom',
      type: 'Cartomancien',
      genre: 'F',
      telephone: '+33 6 91 74 28 60',
      presentation: 'Lit dans l\'ombre les arcanes que la lumière refuse. Tirages en sept lames, miroir et symétrie.',
      detail: { label: 'Jeu rituel', value: 'Tarot Rider–Waite, édition 1909' },
      glyph: 'moon',
    },
    {
      id: 'serena',
      denomination: 'Serena',
      rank: 'III · L\'Impératrice',
      type: 'Astrologue',
      genre: 'F',
      telephone: '+33 6 32 19 87 04',
      presentation: 'Thèmes karmiques et progressions secondaires. Aborde le ciel comme un récit, jamais comme un verdict.',
      detail: { label: 'Formation', value: 'ENS-Astro · Promotion 2006' },
      glyph: 'star',
    },
    {
      id: 'mrm',
      denomination: 'Mr M',
      rank: 'VII · Le Chariot',
      type: 'Astrologue',
      genre: 'H',
      telephone: '+33 6 04 56 71 33',
      presentation: 'Calcule les thèmes natals à la minute près. Cartographe des conjonctions et des éclipses.',
      detail: { label: 'Formation', value: 'Institut des Nouveaux Savoirs Astrologiques · Promo 2010' },
      glyph: 'sun',
    },
  ],
  // Tous les employés (pour la logique d'assignation)
  employes: [
    { id: 'cassandre', prenom: 'Cassandre', nom: 'Lerieux', genre: 'F', email: 'cassandre.lerieux@predict.if', enConsultation: false, consultations: 28 },
    { id: 'mathilde',  prenom: 'Mathilde',  nom: 'Volkov',  genre: 'F', email: 'mathilde.volkov@predict.if',  enConsultation: true,  consultations: 24 },
    { id: 'aldric',    prenom: 'Aldric',    nom: 'Renart',  genre: 'H', email: 'aldric.renart@predict.if',    enConsultation: false, consultations: 21 },
    { id: 'eloise',    prenom: 'Éloïse',    nom: 'Mansour', genre: 'F', email: 'eloise.mansour@predict.if',   enConsultation: false, consultations: 19 },
    { id: 'paolo',     prenom: 'Paolo',     nom: 'Hadès',   genre: 'H', email: 'paolo.hades@predict.if',      enConsultation: false, consultations: 14 },
    { id: 'judith',    prenom: 'Judith',    nom: 'Sefra',   genre: 'F', email: 'judith.sefra@predict.if',     enConsultation: false, consultations: 17 },
  ],
  // Tous les clients (pour résolution email)
  clients: [
    { id: 'eliane', prenom: 'Eliane', nom: 'Morvan', email: 'eliane.morvan@gmail.com' },
  ],
  // Départements français (métropole + Corse + DOM) — l'adresse est forcément en France
  departements: [
    {"code":"01","nom":"Ain"},{"code":"02","nom":"Aisne"},{"code":"03","nom":"Allier"},{"code":"04","nom":"Alpes-de-Haute-Provence"},{"code":"05","nom":"Hautes-Alpes"},{"code":"06","nom":"Alpes-Maritimes"},{"code":"07","nom":"Ardèche"},{"code":"08","nom":"Ardennes"},{"code":"09","nom":"Ariège"},{"code":"10","nom":"Aube"},{"code":"11","nom":"Aude"},{"code":"12","nom":"Aveyron"},{"code":"13","nom":"Bouches-du-Rhône"},{"code":"14","nom":"Calvados"},{"code":"15","nom":"Cantal"},{"code":"16","nom":"Charente"},{"code":"17","nom":"Charente-Maritime"},{"code":"18","nom":"Cher"},{"code":"19","nom":"Corrèze"},{"code":"2A","nom":"Corse-du-Sud"},{"code":"2B","nom":"Haute-Corse"},{"code":"21","nom":"Côte-d’Or"},{"code":"22","nom":"Côtes-d’Armor"},{"code":"23","nom":"Creuse"},{"code":"24","nom":"Dordogne"},{"code":"25","nom":"Doubs"},{"code":"26","nom":"Drôme"},{"code":"27","nom":"Eure"},{"code":"28","nom":"Eure-et-Loir"},{"code":"29","nom":"Finistère"},{"code":"30","nom":"Gard"},{"code":"31","nom":"Haute-Garonne"},{"code":"32","nom":"Gers"},{"code":"33","nom":"Gironde"},{"code":"34","nom":"Hérault"},{"code":"35","nom":"Ille-et-Vilaine"},{"code":"36","nom":"Indre"},{"code":"37","nom":"Indre-et-Loire"},{"code":"38","nom":"Isère"},{"code":"39","nom":"Jura"},{"code":"40","nom":"Landes"},{"code":"41","nom":"Loir-et-Cher"},{"code":"42","nom":"Loire"},{"code":"43","nom":"Haute-Loire"},{"code":"44","nom":"Loire-Atlantique"},{"code":"45","nom":"Loiret"},{"code":"46","nom":"Lot"},{"code":"47","nom":"Lot-et-Garonne"},{"code":"48","nom":"Lozère"},{"code":"49","nom":"Maine-et-Loire"},{"code":"50","nom":"Manche"},{"code":"51","nom":"Marne"},{"code":"52","nom":"Haute-Marne"},{"code":"53","nom":"Mayenne"},{"code":"54","nom":"Meurthe-et-Moselle"},{"code":"55","nom":"Meuse"},{"code":"56","nom":"Morbihan"},{"code":"57","nom":"Moselle"},{"code":"58","nom":"Nièvre"},{"code":"59","nom":"Nord"},{"code":"60","nom":"Oise"},{"code":"61","nom":"Orne"},{"code":"62","nom":"Pas-de-Calais"},{"code":"63","nom":"Puy-de-Dôme"},{"code":"64","nom":"Pyrénées-Atlantiques"},{"code":"65","nom":"Hautes-Pyrénées"},{"code":"66","nom":"Pyrénées-Orientales"},{"code":"67","nom":"Bas-Rhin"},{"code":"68","nom":"Haut-Rhin"},{"code":"69","nom":"Rhône"},{"code":"70","nom":"Haute-Saône"},{"code":"71","nom":"Saône-et-Loire"},{"code":"72","nom":"Sarthe"},{"code":"73","nom":"Savoie"},{"code":"74","nom":"Haute-Savoie"},{"code":"75","nom":"Paris"},{"code":"76","nom":"Seine-Maritime"},{"code":"77","nom":"Seine-et-Marne"},{"code":"78","nom":"Yvelines"},{"code":"79","nom":"Deux-Sèvres"},{"code":"80","nom":"Somme"},{"code":"81","nom":"Tarn"},{"code":"82","nom":"Tarn-et-Garonne"},{"code":"83","nom":"Var"},{"code":"84","nom":"Vaucluse"},{"code":"85","nom":"Vendée"},{"code":"86","nom":"Vienne"},{"code":"87","nom":"Haute-Vienne"},{"code":"88","nom":"Vosges"},{"code":"89","nom":"Yonne"},{"code":"90","nom":"Territoire de Belfort"},{"code":"91","nom":"Essonne"},{"code":"92","nom":"Hauts-de-Seine"},{"code":"93","nom":"Seine-Saint-Denis"},{"code":"94","nom":"Val-de-Marne"},{"code":"95","nom":"Val-d’Oise"},{"code":"971","nom":"Guadeloupe"},{"code":"972","nom":"Martinique"},{"code":"973","nom":"Guyane"},{"code":"974","nom":"La Réunion"},{"code":"976","nom":"Mayotte"}
  ],
  consultations: [
    { id: 'c-2024-03', date: '08 fév. 2026', dateLong: '8 février 2026, 21h30', medium: 'Endora', mediumId: 'endora', type: 'Cartomancien', status: 'done', commentaire: "Le seuil s'est ouvert sans résistance. Présence forte d'une figure maternelle, message bref mais clair sur la transmission. Cliente sereine en fin de session." },
    { id: 'c-2024-02', date: '17 jan. 2026', dateLong: '17 janvier 2026, 19h00', medium: 'Serena', mediumId: 'serena', type: 'Astrologue', status: 'done', commentaire: 'Carte natale révisée, transits 2026 expliqués. Cliente intéressée par la conjonction Vénus–Saturne en avril.' },
    { id: 'c-2023-12', date: '22 déc. 2025', dateLong: '22 décembre 2025, 22h15', medium: 'Mme Irma', mediumId: 'irma', type: 'Cartomancien', status: 'done', commentaire: 'Tirage en croix celtique. La Lune est sortie en position renversée — thème du doute. Suite recommandée.' },
    { id: 'c-2023-09', date: '03 oct. 2025', dateLong: '3 octobre 2025, 20h45', medium: 'Gwenaëlle', mediumId: 'gwenaelle', type: 'Spirite', status: 'done', commentaire: '' },
  ],
  // Statistiques
  stats: {
    consultationsParMedium: [
      { name: 'Endora',          value: 184 },
      { name: 'Serena',          value: 142 },
      { name: 'Gwenaëlle',       value: 119 },
      { name: 'Mme Irma',        value: 96  },
      { name: 'Mr M',            value: 73  },
      { name: 'Professeur Tran', value: 51  },
    ],
    clientsParEmploye: [
      { name: 'C. Lerieux', value: 28 },
      { name: 'M. Volkov',  value: 24 },
      { name: 'A. Renart',  value: 21 },
      { name: 'É. Mansour', value: 19 },
      { name: 'J. Sefra',   value: 17 },
      { name: 'P. Hadès',   value: 14 },
    ],
    top5: [
      { rank: 1, name: 'Endora',          type: 'Cartomancien', value: 184, share: 28 },
      { rank: 2, name: 'Serena',          type: 'Astrologue',   value: 142, share: 22 },
      { rank: 3, name: 'Gwenaëlle',       type: 'Spirite',      value: 119, share: 18 },
      { rank: 4, name: 'Mme Irma',        type: 'Cartomancien', value: 96,  share: 15 },
      { rank: 5, name: 'Mr M',            type: 'Astrologue',   value: 73,  share: 11 },
    ],
    // Répartition des clientes par département (code INSEE → nombre)
    // Métropole + Corse (2A/2B) + DOM (971-976). Les départements absents = aucune cliente.
    clientsParDepartement: {
      '75': 38, '92': 14, '93': 9, '94': 8, '78': 7, '91': 6, '77': 6, '95': 5,
      '69': 22, '13': 18, '59': 15, '33': 13, '31': 12, '34': 11, '44': 10,
      '06': 9, '67': 8, '38': 7, '83': 7, '76': 6, '35': 6, '57': 5, '62': 5,
      '29': 5, '14': 4, '45': 4, '49': 4, '30': 4, '64': 4, '74': 4, '42': 4,
      '37': 3, '63': 3, '21': 3, '51': 3, '54': 3, '85': 3, '17': 3, '56': 3,
      '60': 3, '66': 3, '01': 3, '26': 2, '25': 2, '80': 2, '27': 2, '10': 2,
      '72': 2, '86': 2, '81': 2, '11': 2, '24': 2, '40': 2, '50': 2, '28': 2,
      '22': 2, '87': 2, '88': 2, '71': 2, '02': 2,
      '89': 1, '79': 1, '07': 1, '03': 1, '18': 1, '46': 1, '12': 1, '16': 1,
      '19': 1, '61': 1, '53': 1, '41': 1, '39': 1, '70': 1, '32': 1, '82': 1,
      '47': 1, '65': 1, '09': 1, '48': 1, '15': 1, '43': 1, '23': 1, '36': 1,
      '58': 1, '52': 1, '55': 1, '08': 1, '05': 1, '04': 1, '90': 1,
      '2A': 2, '2B': 2,
      '971': 4, '972': 3, '973': 2, '974': 6, '976': 1,
    },
  },
  inspirations: {
    amour: [
      "Une rencontre liée à l'eau réveillera un sentiment endormi avant la prochaine lune.",
      "Le cœur appelle la patience : une silhouette familière reviendra par le seuil que vous croyiez clos.",
      "Vénus penche vers vous ; ne précipitez pas ce qui veut mûrir dans le silence.",
      "Trois feux brûlent autour de vous — un seul demande à être nourri."
    ],
    sante: [
      "L'énergie revient par le souffle ; les matins clairs vous rendront ce que les nuits ont pris.",
      "Surveillez la racine, pas le sommet : le corps écoute les pieds avant la tête.",
      "Une eau plus juste, une marche plus lente — le remède n'est pas ailleurs.",
      "La pleine lune dégagera ce qui pèse sur la nuque."
    ],
    travail: [
      "Une porte que vous n'aviez pas ouverte attend d'être poussée — elle ne grincera pas.",
      "Le succès viendra par un détour, pas par la route prévue.",
      "Méfiez-vous d'un conseil donné sans qu'on vous l'ait demandé.",
      "Vendredi est votre jour : posez là votre signature ou votre refus."
    ]
  }
};
