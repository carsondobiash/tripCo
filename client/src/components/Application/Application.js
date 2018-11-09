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
import Search from "./Search";

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
          optimization : "none",
          unitName: "",
          unitRadius: null,
          map: "svg"
        },
        places: [],
        distances: [],
        map: '<svg width="1920" height="20" xmlns="http://www.w3.org/2000/svg" xmlns:svg="http://www.w3.org/2000/svg"><g></g></svg>',
      },
      search: {
          type: "search",
          match: "",
          filters:[],
          limit: 0,
          found: 0,
          places: []
      },
      port: location.port,
      host: location.hostname
    }
    this.updateTrip = this.updateTrip.bind(this);
    this.updateBasedOnResponse = this.updateBasedOnResponse.bind(this);
    this.updateOptions = this.updateOptions.bind(this);
    this.updatePlaces = this.updatePlaces.bind(this);
    this.updatePort = this.updatePort.bind(this);
    this.updateHost = this.updateHost.bind(this);
    this.updateBasedOnSearch = this.updateBasedOnSearch.bind(this);
    this.updateName = this.updateName.bind(this);
    this.updateLimit = this.updateLimit.bind(this);
    this.updateID = this.updateID.bind(this);
    this.updateFilter = this.updateFilter.bind(this);
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

  updatePlaces(places){
    let trip = this.state.trip;
    trip.places = places;
    this.setState(trip);
  }

    updatePort(event){
        let portValue = event.target.value;
        this.setState({'port': portValue});
    }

    updateHost(event){
        let hostName = event.target.value;
        this.setState({'host': hostName});
    }

    updateBasedOnSearch(value){
      this.setState({'search': value});
    }

    updateID(id){
      let newID = this.state.search;
      newID.id = id;
      this.setState(newID);
    }

    updateName(name){
      let search = this.state.search;
      search.match = name;
      this.setState();
    }

    updateLimit(limit){
      let search = this.state.search;
      search.limit = limit;
      if(search.limit === "")
        search.limit = 0;
      this.setState();
    }

    updateFilter(filter){
      let temp = this.state.search;
      let newFilter = this.state.search.filters;
      let tempFilter = newFilter.concat(filter);
      temp.filters = tempFilter;
      this.setState({search: temp});
    }


  render() {
    if(!this.state.config) { return <div/> }

    return(
      <Container id="Application">
        <Info/>
        <Customize trip={this.state.trip} updatePlaces={this.updatePlaces}/>
          <Options options={this.state.trip.options} config={this.state.config} updateOptions={this.updateOptions} port={this.state.port} host={this.state.host} updatePort={this.updatePort} updateHost={this.updateHost}/>
        <Card>
          <Trip trip={this.state.trip} updateTrip={this.updateTrip} updateBasedOnResponse={this.updateBasedOnResponse} port={this.state.port} host={this.state.host}/>
          <Search trip={this.state.trip} search={this.state.search} updateBasedOnSearch={this.updateBasedOnSearch} port={this.state.port} host={this.state.host} updateName={this.updateName}
                  updateLimit={this.updateLimit} updateID={this.updateID} updatePlaces={this.updatePlaces} updateFilter={this.updateFilter}/>
          <Map trip={this.state.trip} updateTrip={this.updateBasedOnResponse}/>
          <Itinerary trip={this.state.trip} config={this.state.config}/>
        </Card>
        <Distance port={this.state.port} host={this.state.host}/>

      </Container>
    )
  }
}

export default Application;
