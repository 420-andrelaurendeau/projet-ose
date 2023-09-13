import {Component} from "react";
import axios from "axios";

class EtudiantInscription extends Component<{},{matricule:string, nom:string, prenom:string, courriel:string, telephonne:string,errors:any}> {
    constructor(props: any) {
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
    handleChange(event:any) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        console.log(name + " " + value);
        // @ts-ignore
        this.setState({
            [name]: value
        });
    }
    handleSubmit(event: any) {
        const {matricule, nom, prenom, courriel, telephonne}:any = this.state;
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
    render(){
        return (
            <div className={"flex flex-col items-center justify-center h-screen bg-white"}>
                <div className={"mb-10"}>
                    <h1 className={"scale-150 text-orange"}>Inscription de l'etudiant</h1>
                </div>
                <form className={"bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4"} onSubmit={this.handleSubmit}>
                    <div className={"mb-4"}>
                        <label className={"block text-gray-700 text-sm font-bold mb-2"}>Matricule:</label>
                        <input placeholder={"1111111"} className={"shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"} type="text" name={"matricule"} value={this.state.matricule} onChange={this.handleChange} />
                    </div>
                    <div className={"mb-4"}>
                        <label className={"block text-gray-700 text-sm font-bold mb-2"}>Nom:</label>
                        <input placeholder={"Jean"} className={"shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"} type="text" name={"nom"} value={this.state.nom} onChange={this.handleChange} />
                    </div>
                    <div className={"mb-4"}>
                        <label className={"block text-gray-700 text-sm font-bold mb-2"}>Prenom:</label>
                        <input placeholder={"Pierre"} className={"shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"} type="text" name={"prenom"} value={this.state.prenom} onChange={this.handleChange} />
                    </div>
                    <div className={"mb-4"}>
                        <label className={"block text-gray-700 text-sm font-bold mb-2"}>Courriel:</label>
                        <input placeholder={"email@email.com"} className={"shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"} type="text" name={"courriel"} value={this.state.courriel} onChange={this.handleChange} />
                    </div>
                    <div className={"mb-4"}>
                        <label className={"block text-gray-700 text-sm font-bold mb-2"}>Telephonne:</label>
                        <input placeholder={"450-450-4500"} className={"shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"} type="text" name={"telephonne"} value={this.state.telephonne} onChange={this.handleChange} />
                    </div>
                    <br></br>
                    <button className={"text-orange border hover:bg-orange hover:text-white text-black py-2 px-4 rounded"} type="submit">Inscription</button>
                </form>
            </div>
        )
    }
}

export default EtudiantInscription;