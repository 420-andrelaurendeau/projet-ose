package com.sap.ose.projetose;

import com.sap.ose.projetose.modeles.*;
import com.sap.ose.projetose.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class ProjetOseApplication implements CommandLineRunner {
    private final ProgrammeRepository programmeRepository;
    private final EtudiantRepository etudiantRepository;
    private final EmployeurRepository employeurRepository;
    private final InternshipCandidatesRepository internshipCandidatesRepository;
    private final FileEntityRepository fileEntityRepository;
    private final InternshipmanagerRepository internshipManagerRepository;
    private final InternOfferRepository internOfferRepository;
    private final OfferReviewRequestRepository offerReviewRequestRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProjetOseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Programme programmeInformatique = programmeRepository.save(new Programme("Techniques de l'informatique", "Programme de formation en techniques de l'informatique"));
        Programme programmeAdmnistration = programmeRepository.save(new Programme("Techniques de l'administration", "Programme de formation en techniques de l'administration"));
        Programme programmeLogistique = programmeRepository.save(new Programme("Techniques de la logistique", "Programme de formation en techniques de la logistique"));
        Programme programmeComtabiliteGestion = programmeRepository.save(new Programme("Techniques de la comptabilité et de la gestion", "Programme de formation en techniques de la comptabilité et de la gestion"));

        Etudiant etudiantMarcMax = etudiantRepository.save(new Etudiant("Marc", "Max", "4387999889", "max@gmail.com", "popo", programmeInformatique, "2045888"));
        Etudiant etudiantLoicLac = etudiantRepository.save(new Etudiant("Loic", "Lac", "4352996589", "Lac@gmail.com", "popo", programmeInformatique, "2045898"));
        Etudiant etudiantJeanDupont = new Etudiant("Jean", "Dupont", "4387996589", "dupont@gmail.com", "popo", programmeAdmnistration, "2045878");


        Employeur employeurPatriqueLemieux = employeurRepository.save(new Employeur("Patrique", "Lemieux", "lemieux@gmail.com", "4383006589", "popo123", "popo", Set.of(programmeInformatique)));
        Employeur employeurPierreLacroix = employeurRepository.save(new Employeur("Pierre", "Lacroix", "lacroix@gmail.com", "4387996589", "popo123", "poo", Set.of(programmeAdmnistration)));

        Internshipmanager internshipmanager = internshipManagerRepository.save(new Internshipmanager("Jean", "Dupont", "4387996589", "dupont@gmail.com", "popo", Set.of(programmeInformatique)));

        OfferReviewRequest offerReviewRequest = offerReviewRequestRepository.save(new OfferReviewRequest());

        File fileApplicationMarc = fileEntityRepository.save(new File("Application".getBytes(StandardCharsets.UTF_8), "Application", Etats.APPROVED, etudiantMarcMax));
        File fileOffreInformatique = fileEntityRepository.save(new File("OffreInformatique".getBytes(StandardCharsets.UTF_8), "OffreInformatique", Etats.APPROVED, employeurPatriqueLemieux));
        File fileOffreAdministration = fileEntityRepository.save(new File("OffreSecurite".getBytes(StandardCharsets.UTF_8), "OffreSecurite", Etats.APPROVED, etudiantMarcMax));

        InternOffer internOfferInformatique = internOfferRepository.save(new InternOffer(
                "Architecte d’Application Cloud Senior / Senior Cloud Application Architect, WWPS PROSERVE",
                "Montréal, QC",
                "Êtes-vous un développeur qui a une expérience pratique en conception d'applications natives pour le nuage? Souhaitez-vous travailler avec nos clients pour les aider à architecturer, développer et réorganiser des applications afin de tirer pleinement parti du nuage AWS ?\\n\\nAimeriez-vous travailler sur une variété de projets stratégiques qui sont à l'avant-garde du développement d'applications et de l'adoption de l'infonuagique?\\n\\nAWS est à la recherche d'architectes techniques et de développeurs seniors talentueux et pragmatiques pour contribuer à l'accélération de la croissance de nos services professionnels pour le secteur public. Il s'agit d'une excellente opportunité de rejoindre l'équipe technique de classe mondiale d'AWS, de travailler avec certains des ingénieurs les plus brillants tout en développant vos compétences au sein de l'une des entreprises technologiques les plus innovantes et avant-gardistes. Au sein des services professionnels, nous nous engageons dans une grande variété de projets critiques et de hauts niveaux pour les clients et les partenaires et nous les aidons à mieux exploiter la plate-forme AWS en constante évolution.\\n\\nNotre équipe collabore avec l'ensemble de l'organisation AWS afin d'avoir accès aux équipes de produits et de services, d'obtenir la bonne solution et d'innover en fonction des besoins des clients.\\n\\n-\\n\\nAre you a developer who has hands-on experience with building cloud-native applications? Would you like to work with our customers to help them architect, develop and re-engineer applications to fully leverage the AWS Cloud?\\n\\nDo you like to work on a variety of cutting edge, business-critical projects at the forefront of application development and cloud technology adoption?\\n\\nAWS is looking for talented, hands-on technical architects and senior developers to help accelerate our growing Public Sector Professional Services business. This is an excellent opportunity to join AWS’s world-class technical team, working with some of the best and brightest engineers while also developing your skills at one of the most innovative and progressive technology companies. Here in Professional Services, we engage in a wide variety of business-critical, high-profile projects for customers and partners and help them better leverage the ever-evolving AWS platform.\\n\\nOur team collaborates across the entire AWS organization to bring access to product and service teams, to get the right solution delivered and drive feature innovation based upon customer needs.\\n\\nKey job responsibilities\\nNous recherchons des architectes/développeurs qui sont passionnés par :\\n\\n    Aider les clients à concevoir des solutions applicatives évolutives et hautement disponibles qui tirent parti des services AWS.\\n    Concevoir et développer des applications clients optimisées pour l'infonuagique.\\n    Travailler en tant que leader technique aux côtés des équipes affaires, développement et infrastructure des clients.\\n    Fournir des connaissances approfondies en matière de développement de logiciels en ce qui concerne l'architecture infonuagique, les modèles de conception et la programmation.\\n    Conseiller et mettre en œuvre les meilleures pratiques AWS\\n    Travailler à la fois en tant que spécialiste de l'infrastructure et du développement d'applications\\n    Mettre en œuvre les pratiques DevOps telles que l'infrastructure en tant que code, l'intégration continue et le déploiement automatisé.\\n\\n\\n-\\n\\nWe are looking for hands-on architects/developers who are passionate about:\\n\\n    Helping customers architect scalable, highly available application solutions that leverage AWS services\\n    Architecting and developing customer applications to be cloud optimized\\n    Working as a technical leader alongside customer business, development and infrastructure teams\\n    Providing deep software development knowledge with respect cloud architecture, design patterns and programming\\n    Advising and implementing AWS best practices\\n    Working as both an infrastructure and application development specialist\\n    Implementing DevOps practices such as infrastructure as code, continuous integration and automated deployment\\n\\n\\nAbout the team\\nEn tant que membre de l'équipe des services professionnels AWS, vous rejoignez une équipe qui investit dans votre réussite en proposant des programmes complets d'apprentissage et de mentorat.\\nNotre équipe accorde également une grande importance à l'équilibre entre vie professionnelle et vie privée. Trouver un équilibre sain entre votre vie personnelle et professionnelle est crucial pour votre bonheur et votre réussite ici, c'est pourquoi nous ne nous focalisons pas sur le nombre d'heures que vous passez au travail ou en ligne. Au contraire, nous sommes heureux de vous proposer un horaire flexible afin que vous puissiez avoir une vie plus productive et équilibrée, tant au travail qu'en dehors.\\n\\n-\\n\\nAs a member of the AWS Professional Services team, you are joining a team that invests in your success by providing comprehensive learning and mentorship programs.\\nOur team also puts a high value on work-life balance. Striking a healthy balance between your personal and professional life is crucial to your happiness and success here, which is why we aren’t focused on how many hours you spend at work or online. Instead, we’re happy to offer a flexible schedule so you can have a more productive and well-balanced life—both in and outside of work.\\n\\nWe are open to hiring candidates to work out of one of the following locations:\\n\\nMontreal, QC, CAN | Quebec City, QC, CAN\\nBASIC QUALIFICATIONS\\n\\n    8+ ans d'expérience en développement de logiciels à grande échelle ou en ingénierie d'application avec une expérience récente de codage dans deux ou plusieurs langages de programmation modernes tels que : Java, JavaScript, C/C++, C#, Swift, Node.js, Python, Go ou Ruby.\\n    5+ ans d'expérience avec l'intégration continue et la livraison continue (CI/CD)\\n    2+ ans d'expérience dans la conception et la mise en oeuvre de technologies infonuagiques\\n    2+ ans d'expérience avec les normes de conformité et de sécurité fédérales et provinciales canadiennes et les classifications de données protégées.\\n    Baccalauréat ou expérience équivalente requis\\n    Maîtrise du français et de l'anglais\\n    Puisque ce rôle nécessite que l’employé interagisse avec d’autres entités d’Amazon à l’échelle mondiale ainsi qu’avec des employés et intervenants dans d’autres provinces canadiennes, la connaissance du français et de l’anglais est exigée pour ce poste.\\n\\n\\n___________________________________________\\n\\n    8+ years experience of large-scale software development or application engineering with recent coding experience in two or more modern programing languages such as: Java, JavaScript, C/C++, C#, Swift, Node.js, Python, Go, or Ruby\\n    5+ years Experience with Continuous Integration and Continuous Delivery (CI/CD)\\n    2+ years experience in the design and provision of cloud technologies\\n    2+ years experience with Canadian Federal and Provincial compliance and security standards and protected data classifications\\n    Bachelor’s degree or equivalent experience required\\n    Fluent in French and English\\n    Due to the nature of the role that requires interaction with other Amazon entities globally and with Amazon employees and stakeholders in other provinces in Canada, bilingualism French and English is required for this position\\n\\nPREFERRED QUALIFICATIONS\\n\\n    Habilitation de sécurité auprès du gouvernement fédéral du Canada ou capacité à l'obtenir.\\n    Capacité établie à réfléchir stratégiquement aux enjeux affaires, produits et techniques.\\n    Solides compétences en communication orale et écrite, avec la capacité de travailler et de diriger efficacement au sein d'organisations internes et externes.\\n    Expérience préalable dans un rôle de services-conseils\\n    Expérience pratique démontrée dans la création d'applications sophistiquées natives pour le nuage.\\n    Certification AWS ou autre CSP\\n    Expérience avec les dépôts de code source et le contrôle de révision\\n    Expérience avec les scripts de construction automatisés utilisés pour la gestion des versions dans tous les environnements.\\n    Expérience avec les outils de tests automatisés\\n    Compréhension et expérience de l'architecture orientée services (SOA et REST).\\n    Capacité à être mobile et à voyager pour des projets\\n\\n\\n____________________________________________\\n\\n    Security clearance with the Federal Government of Canada or an ability to obtain it.\\n    Demonstrated ability to think strategically about business, product, and technical challenges\\n    Strong verbal and written communication skills, with the ability to work and lead effectively across internal and external organizations\\n    Prior experience in a consulting role\\n    Demonstrated hands on experience building sophisticated cloud native applications\\n    Certification in AWS or other CSP\\n    Experience with revision control source code repositories\\n    Experience with seamless/automated build scripts used for release management across all environments\\n    Experience with automated testing tools\\n    Understanding of and experience with Service-Oriented Architecture (SOA and REST)\\n    Ability to be mobile and travel for projects\\n\\n\\nAmazon s’engage à créer un milieu de travail diversifié et inclusif. Amazon est un employeur qui offre à tous les mêmes opportunités et ne fait pas de discrimination fondée sur la race, l’origine nationale, le sexe, l’identité sexuelle, l’orientation sexuelle, le statut d’ancien combattant protégé, le handicap, l’âge ou autres statuts protégés par la loi. Les personnes handicapées qui souhaitent présenter une demande d’accommodement, sont invitées à aviser leur recruteur.\\n\\nAmazon is committed to a diverse and inclusive workplace. Amazon is an equal opportunity employer and does not discriminate on the basis of race, national origin, gender, gender identity, sexual orientation, disability, age, or other legally protected status. If you would like to request an accommodation, please notify your Recruiter.\\n",
                23,
                LocalDate.of(2024,1,1),
                LocalDate.of(2024,5,1),
                Etats.APPROVED,
                new ArrayList<>(),
                programmeInformatique,
                fileOffreInformatique,
                employeurPatriqueLemieux,
                offerReviewRequest));

        InternOffer internOfferSecurite = internOfferRepository.save(new InternOffer(
                "Security Guards",
                "1455 Rue Peel, Montréal, QC",
                "Downtown complex is recruiting full time security guards for day and night shifts possessing the following qualifications:\\n\\n- Must have a valid Quebec BSP License\\n\\n- 100% bilingual French and English\\n\\n- Detail oriented; fast learner\\n\\n- Team player\\n\\n- No experience required\\n\\n- Reliable; responsible\\n\\nWe offer a good salary, and a pleasant working atmosphere.\\n\\nIf you are interested in this position, please email your CV. We are an equal opportunity employer.\\n\\nJob Types: Permanent, Full-time\\n\\nSchedule:\\n\\n    8 hour shift\\n    Day shift\\n    Monday to Friday\\n    Night shift\\n",
                20,
                LocalDate.of(2024, 2, 12),
                LocalDate.of(2025, 2, 12),
                Etats.APPROVED,
                new ArrayList<>(),
                programmeInformatique,
                fileOffreAdministration,
                employeurPierreLacroix,
                offerReviewRequest));

        InternshipCandidates internshipCandidatesMax = internshipCandidatesRepository.save(new InternshipCandidates(etudiantMarcMax, internOfferInformatique, List.of(fileApplicationMarc)));
    }
}
