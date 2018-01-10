export class User{

    id:number;
    name:String;
    lastname:String;
    mail:String;
    age:number;
    telephone:String;

    constructor(){}
    
    getName(){
        return this.name;
    }
    getLastName(){
        return this.lastname;
    }
    getMail(){
        return this.mail;
    }
    getAge(){
        return this.age;
    }
    getTelephone(){
        return this.telephone;
    }
    getId(){
        return this.id;
    }

    setName(name:String){
        this.name = name;
    }
    setLastName(lastname:String){
        this.lastname = lastname;
    }
    setMail(mail:String){
        this.mail = mail;
    }
    setAge(age:number){
        this.age =age ;
    }
    setTelephone(telephone:String){
        this.telephone =telephone ;
    }
    setId(id:number){
        this.id =id ;
    }
}