import React, {useState} from 'react'
import '../style/import.css';
import { useNavigate } from 'react-router-dom'
import { personajes } from "../characters/personajes"

import InfoCharacter from '../components/InfoCharacter';
import Header from '../components/Header';
import ProfileIcon from '../components/ProfileIcon';


function Selector() {
  const nav = useNavigate();

  /* Variables actualizables para saber que jugador eligió o no ya. */
  const [jugador1,setEstado] = useState(false); const [jugador2,setEstado2] = useState(false);

  /* Variables con el personaje seleccionado por el jugador 1/2. Se actualizará según el indice actualizable según el personaje elegible de los iconos. */
  const [index, setIndex] = useState(0);
  const personaje1 = personajes[index];
  const [index2, setIndex2] = useState(0);
  const personaje2 = personajes[index2];

  return (
    <div className={'padre'}>
        <div className={'marco fondo'}>
          
          <Header direccion={-1}/>

          {/*--------------------*/}

          <div className={'cuerpo'}>
            <div className={'elegirPersonaje'}>
                <div className={'informacion'}>
                    <InfoCharacter jugador={personaje1}/>
                    <InfoCharacter jugador={personaje2}/>
                </div>

                <div className={'minipersonajes'}>
                    <div>
                        <img src={personaje1.img1} alt="personaje 1" onClick={() => {setIndex(0); setEstado(false);}}/>
                    </div>
                    <div>
                        <img src={personaje2.img1} alt="personaje 2" onClick={() => {setIndex2(0); setEstado2(false);}}  className={'segundo'}/>
                    </div>
                </div>

                <div className={'selector'}>
                  <ProfileIcon icon={"KukaIcono"} num={1} cambiar={cambiar}/>
                  <ProfileIcon icon={"PacoIcono"} num={2} cambiar={cambiar}/>
                  <ProfileIcon icon={"DioneIcono"} num={3} cambiar={cambiar}/>
                </div>
                
                <button type="button" onClick={() => jugador1 && jugador2 ? nav(`/combate/${index}/${index2}`) : null}>Listo</button>
            </div>
          </div>
        </div>
    </div>
  )


  /* Funcion para cambiar el estado. 
  Lo que hará será que según la imágen clickeada, la cual se pasará el indice por parámetro, cambiará el indice del jugador 1 o 2 según si ya eligieron o no. */
  function cambiar(id) {
    if (!jugador1) {
      setEstado(true);
      setIndex(id)
    } else if (!jugador2) {
      setEstado2(true);
      setIndex2(id);
    } else {
      quitarPersonaje(id);
    }
  }
  /* Funcion para quitar el personaje.
  Si se selecciona un icono se compara el index actual con el id de ese icono, removiendo el personaje y haciendo que el estado del jugador sea falso.*/
  function quitarPersonaje(id) {
    if (index == id) {
      setIndex(0);
      setEstado(false);
    } else {
      setIndex2(0);
      setEstado2(false);
    }
  }
}



export default Selector
