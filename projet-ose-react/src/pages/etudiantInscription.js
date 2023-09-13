import {Component} from "react";
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
            <div className={"flex flex-col items-center justify-center h-screen bg-white"}>
                <div className={"mb-10"}>
                    <h1 className={"scale-150 text-orange"}>Inscription de l'etudiant</h1>
                </div>
                <form className={"bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4"} onSubmit={this.handleSubmit}>
                    <div className={"mb-4"}>
                        <label className={"block text-gray-700 text-sm font-bold mb-2"}>Matricule:</label>
                        <input className={"shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"} type="text" name={"matricule"} value={this.state.value} onChange={this.handleChange} />
                    </div>
                    <div className={"mb-4"}>
                        <label className={"block text-gray-700 text-sm font-bold mb-2"}>Nom:</label>
                        <input className={"shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"} type="text" name={"nom"} value={this.state.value} onChange={this.handleChange} />
                    </div>
                    <div className={"mb-4"}>
                        <label className={"block text-gray-700 text-sm font-bold mb-2"}>Prenom:</label>
                        <input className={"shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"} type="text" name={"prenom"} value={this.state.value} onChange={this.handleChange} />
                    </div>
                    <div className={"mb-4"}>
                        <label className={"block text-gray-700 text-sm font-bold mb-2"}>Courriel:</label>
                        <input className={"shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"} type="text" name={"courriel"} value={this.state.value} onChange={this.handleChange} />
                    </div>
                    <div className={"mb-4"}>
                        <label className={"block text-gray-700 text-sm font-bold mb-2"}>Telephonne:</label>
                        <input className={"shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"} type="text" name={"telephonne"} value={this.state.value} onChange={this.handleChange} />
                    </div>
                    <br></br>
                    <button className={"text-orange border hover:bg-orange hover:text-white text-black py-2 px-4 rounded"} type="submit">Inscription</button>
                </form>
            </div>
        )
    }
}

export default EtudiantInscription;