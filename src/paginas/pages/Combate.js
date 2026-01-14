import React, {useState} from 'react'
import '../style/import.css';
import { useNavigate, useParams } from 'react-router-dom'
import { personajes } from "../characters/personajes"
import orbeIcono from "../assets/elementos/orbeIcono.png"
import corazon from "../assets/elementos/corazonIcono.png"
import golpeSonido from "../assets/audios/golpe.wav"
import perderSondio from "../assets/audios/perder.wav"

import Stats from '../components/Stats';
import Header from '../components/Header';
import DisplayedNumGenerated from '../components/DisplayedNumGenerated';
import Sprites from '../components/Sprites';

function Combate() {
  const nav = useNavigate();
  const params = useParams();

  //Botones disabled o no
  const [acumular, setDisabled] = useState(false);
  const [pelear, setDisabled2] = useState(true);

  const jugador1 = personajes[params.index];
  const jugador2 = personajes[params.index2];

  //Stats
  //Con que pelearan.
  var lista = ["", "fuerza", "destreza", "poder"];
  const [num, setNum] = useState(0);
  const seleccion = lista[num];

  //Jugador 1
  const [poderAcumulado, setPoder] = useState(0);
  const [vidas, setVida] = useState(3);

  //Jugador 2
  const [poderAcumulado2, setPoder2] = useState(0)
  const [vidas2, setVida2] = useState(3);


  const [victoria, setVictoria] =useState("");

  return (
    <div className={'padre'}>
        <div className={'marco fondo'}>

          <Header direccion={-1}/>

          {/*--------------------*/}

          <div className={'cuerpo'}>
            <div className={'estadio'}>
              <div className={"resultadoVencedor"}>
                <p>{victoria}</p>
              </div>

              <div className={'variables'}>
                <DisplayedNumGenerated icon={orbeIcono} power={poderAcumulado} ide={""}/>

                <div  className={'glow'}>
                  <p id={"statFinal"}>{seleccion}</p>
                </div>
                
                <DisplayedNumGenerated icon={orbeIcono} power={poderAcumulado2} ide={"voltear"}/>
                
              </div>

              <div className={'vidas'}>
                <div><img src={corazon} alt="vida" id={"vida1"}/><img src={corazon} alt="vida" id={"vida2"}/><img src={corazon} alt="vida" id={"vida3"}/></div>
                <div><img src={corazon} alt="vida" id={"vida12"}/><img src={corazon} alt="vida"  id={"vida22"}/><img src={corazon} alt="vida" id={"vida32"}/></div>
              </div>

              <div className={'sprites'}>
                <Sprites jugador={jugador1} ide={"primerJugador"} clase={""} />
                <Sprites jugador={jugador2} ide={"segundoJugador"} clase={"segundo"} />
              </div>

              <div className={'abajos'}>
                <Stats jugador={jugador1}/>

                <div className={'glow'}>
                  <a onClick={() => seleccionAtributo()} className={acumular ? "disabled":""}>ACUMULAR PODER</a>
                  <a onClick={()=> numeroDado()} className={pelear ? "disabled":""}id={"pelear"}>PELEAR</a>
                  <a onClick={() => nav(-1)}>REGRESAR AL VOID</a>
                </div>

                <Stats jugador={jugador2}/>

              </div>
            </div>
          </div>
        </div>
    </div>
  )

  //FUNCIONES
  function seleccionAtributo() { //Para elegir con que stat se peleará
    setNum(Math.floor(Math.random() * (3-1+1)+1));

    //Desactivar este "boton"
    setDisabled(true);
    setDisabled2(false);

    
  }
  function numeroDado() { //Número aleatorio que se sumará junto al stat
    var aux1 = Math.floor(Math.random() * (100-1+1)+1);
    setPoder(aux1);
    var aux2 = Math.floor(Math.random() * (100-1+1)+1);
    setPoder2(aux2);

    //Desactivar este "boton"
    setDisabled(false);
    setDisabled2(true);

    enfrentamiento(aux1, aux2)
  }

  //Funcion para calcular quien de los dos jugadores gana en el enfrentamiento
  function enfrentamiento(aux1, aux2) {
    var total1= aux1 + jugador1[seleccion];
    var total2= aux2 + jugador2[seleccion];
    
    if (total1>total2) {
      switch (vidas2) {
        case 3: document.getElementById("vida12").style.display = "none"; setVida2(2); break;
        case 2: document.getElementById("vida22").style.display = "none"; setVida2(1); break;
        case 1: document.getElementById("vida32").style.display = "none"; setVida2(0); document.getElementById("segundoJugador").classList.add("gris"); 
        setDisabled(true);
        derrotado();
        setVictoria("JUGADOR 1 HA GANADO");
        break;
      }
      sacudir("segundoJugador");
      golpeado();
    } else if (total2>total1) {
      switch (vidas) {
        case 3: document.getElementById("vida3").style.display = "none"; setVida(2); break;
        case 2: document.getElementById("vida2").style.display = "none"; setVida(1); break;
        case 1: document.getElementById("vida1").style.display = "none"; setVida(0); document.getElementById("primerJugador").classList.add("gris"); 
        setDisabled(true);
        derrotado();
        setVictoria("JUGADOR 2 HA GANADO");
        break;
      }
      sacudir("primerJugador");
      golpeado();
    }
  }

  //Se usa DOM para añadir y borrar la animacion de movimiento al recibir un golpe.
  function sacudir(texto) {
    const animacion = document.getElementById(texto);
    animacion.classList.remove("sacudir");
    void animacion.offsetWidth;
    animacion.classList.add("sacudir");
  }

  //SONIDOS
  function golpeado() {
    const golpe = new Audio(golpeSonido)
    golpe.play();
  }
  function derrotado() {
    const muerte = new Audio(perderSondio)
    muerte.play()
  }
}
/*
Borrar y añadir clases Js: https://www.w3schools.com/howto/howto_js_remove_class.asp
Ocultar y mostar: https://www.w3schools.com/howto/howto_js_toggle_hide_show.asp
Reiniciar animacion: https://stackoverflow.com/questions/60686489/what-purpose-does-void-element-offsetwidth-serve
Reproducir audio: https://www.delftstack.com/es/howto/javascript/play-audio-javascript/
*/
export default Combate