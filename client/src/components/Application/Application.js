import React, {Component} from 'react';
import { Container } from 'reactstrap';
import { Card, CardHeader, CardBody } from 'reactstrap'
import Info from './Info'
import Options from './Options';
import Map from './Map';
import Trip from './Trip';
import Customize from './Customize'

import { get_config } from '../../api/api';
import Itinerary from "./Itinerary";
import Distance from "./Distance";

/* Renders the application.
 * Holds the destinations and options state shared with the trip.
 */
class Application extends Component {
  constructor(props){
    super(props);
    this.state = {
      config: null,
      trip: {
        type: "trip",
        title: "",
        options : {
          units: "miles",
          unitName: "",
          unitRadius: null,
        },
        places: [],
        distances: [],
        map: '<svg width="1920" height="20" xmlns="http://www.w3.org/2000/svg" xmlns:svg="http://www.w3.org/2000/svg"><g></g></svg>'
      }
    };
    this.updateTrip = this.updateTrip.bind(this);
    this.updateBasedOnResponse = this.updateBasedOnResponse.bind(this);
    this.updateOptions = this.updateOptions.bind(this);
  }

  componentWillMount() {
    get_config().then(
      config => {
        this.setState({
          config:config
        })
      }
    );
  }

  updateTrip(field, value){
    let trip = this.state.trip;
    trip[field] = value;
    this.setState(trip);
  }

  updateBasedOnResponse(value) {
    this.setState({'trip': value});
  }

  updateOptions(option, value){
      let trip = this.state.trip;
      trip.options[option] = value;
      this.setState(trip);
    }

  updatePlaces(places){};


  render() {
    if(!this.state.config) { return <div/> }

    return(
      <Container id="Application">
        <Info/>
        <Options options={this.state.trip.options} config={this.state.config} updateOptions={this.updateOptions}/>
        <Customize trip={this.state.trip}/>
        <Card>
          <Trip trip={this.state.trip} updateBasedOnResponse={this.updateBasedOnResponse}/>
          <Map trip={this.state.trip} updateTrip={this.updateBasedOnResponse}/>
          <Itinerary trip={this.state.trip}/>
        </Card>
        <Distance/>
      </Container>
    )
  }
}

export default Application;