function etudiantInscription() {
    return (
        <div>
            <h1>Inscription</h1>
            <form>
                <label>n° matricule</label>
                <input type="text" name="numEtudiant" />
                <label>nom</label>
                <input type="text" name="nom" />
                <label>prénom</label>
                <input type="text" name="prenom" />
                <label>courriel</label>
                <input type="text" name="courriel" />

                <button type="submit">s'inscrire</button>
            </form>
        </div>
    )
}

export default etudiantInscription