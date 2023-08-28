import React, { useState, useEffect } from 'react';
import Map from './Map/map';
import { getRecommendations } from './Api';

const App = () => {
    const [places, setPlaces] = useState([]);
    useEffect (() =>{
        getRecommendations()
        .then((data) => {
            console.log(data);
            setPlaces(data);
        })
    }, []);
    return (
        <div>
            <h1>Hello World!</h1>
            <Map></Map>
        </div>
    );
}

export default App;