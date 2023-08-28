import React from 'react';
import GoogleMapReact from 'google-map-react';

const Map = () => {
    const coord = {lat: 0, lng: 0};
    const apiKey = process.env.REACT_APP_GOOGLE_MAP_API_KEY;
    return (
        <div style = {{ height: '100vh', width: '100%'}}>
            <GoogleMapReact
                bootstrapURLKeys={{key: apiKey}}
                defaultCenter={coord}
                defaultZoom={11}
            >
            </GoogleMapReact>
        </div>
        
    );
}

export default Map;