import {Component, useState} from "react";
import axios from "axios";

class EtudiantInscription extends Component {
    constructor(props) {
        super(props)
        this.state = {
            matricule: '',
            nom: '',
            prenom: '',
            courriel: '',
            telephonne: '',
            errors: {
                matricule: '',
                nom: '',
                prenom: '',
                courriel: '',
                telephonne: ''
            }
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    //handle change for multiple input with different name
    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        console.log(name + " " + value);
        this.setState({
            [name]: value
        });
    }
    handleSubmit(event) {
        const {matricule, nom, prenom, courriel, telephonne} = this.state;
        event.preventDefault();
        alert(`Votre matricule est ${matricule} ${nom} ${prenom} ${courriel} ${telephonne}`)
        axios.post('http://localhost:8080/etudiantInscription', {
            matricule: matricule,
            nom: nom,
            prenom: prenom,
            courriel: courriel,
            telephonne: telephonne
        }).then(r => {
            console.log(r)
        }).catch(e => {
            console.log(e)
        }
        )
    }
    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <div>
                    <h1>Inscription de l'etudiant</h1>
                    <label>
                        <input type="text" name={"matricule"} value={this.state.value} onChange={this.handleChange} />
                    </label>
                    <br></br>
                    <label>
                        Nom:
                        <input type="text" name={"nom"} value={this.state.value} onChange={this.handleChange} />
                    </label>
                    <br></br>
                    <label>
                        Prenom:
                        <input type="text" name={"prenom"} value={this.state.value} onChange={this.handleChange} />
                    </label>
                    <br></br>
                    <label>
                        Courriel:
                        <input type="text" name={"courriel"} value={this.state.value} onChange={this.handleChange} />
                    </label>
                    <br></br>
                    <label>
                        telephonne:
                        <input type="text" name={"telephonne"} value={this.state.value} onChange={this.handleChange} />
                    </label>
                    <br></br>
                    <button type="submit">Inscription</button>
                </div>
            </form>
        )
    }
}

export default EtudiantInscription;