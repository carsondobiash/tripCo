import React, {Component} from 'react';
import {Container, Nav, NavItem, NavLink} from 'reactstrap';
import { Card, CardBody, CardTitle, TabContent, TabPane  } from 'reactstrap'
import Info from './Info'
import Options from './Options';
import Map from './Map';
import Trip from './Trip';
import Customize from './Customize'
import { get_config } from '../../api/api';
import Itinerary from "./Itinerary";
import Distance from "./Distance";
import Search from "./Search";
import Bio from "./Bio";

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
      host: location.hostname,
      tab: '1'
    };
    this.updateTrip = this.updateTrip.bind(this);
    this.updateBasedOnResponse = this.updateBasedOnResponse.bind(this);
    this.updateOptions = this.updateOptions.bind(this);
    this.updatePlaces = this.updatePlaces.bind(this);
    this.updatePort = this.updatePort.bind(this);
    this.updateHost = this.updateHost.bind(this);
    this.updateBasedOnSearch = this.updateBasedOnSearch.bind(this);
    this.updateFilter = this.updateFilter.bind(this);
    this.removeFilter = this.removeFilter.bind(this);
    this.updateServer = this.updateServer.bind(this);
    this.updateSearch = this.updateSearch.bind(this);
    this.toggleTab = this.toggleTab.bind(this);
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

    updateSearch(field, value){
        let search = this.state.search;
        search[field] = value;
        if(field === "limit"){
            if(value === ""){
                search[field] = 0;
            }
        }
        this.setState(search);
    }

    updateFilter(filter){
      let temp = this.state.search;
      let newFilter = this.state.search.filters;
      let tempFilter = newFilter.concat(filter);
      temp.filters = tempFilter;
      this.setState({search: temp});
    }

    removeFilter(){
        let temp = this.state.search;
        temp.filters = [];
        this.setState({search: temp});
    }

    updateServer(){
      if(this.state.host === ""){
        this.setState({host: location.hostname})
      }
      if(this.state.port === ""){
        this.setState({port: location.port})
      }
      get_config(type, this.state.host, this.state.port).then(config => {this.setState({config: config})});
    }

    toggleTab(tab){
      if(this.state.tab !== tab){
        this.setState({
            tab: tab
        });
      }
    }


  render() {
    if(!this.state.config) { return <div/> }

      let navbar =
          <Nav tabs>
              <NavItem>
                  <NavLink
                      className={this.state.tab === '1' ? "active" : "" }
                      onClick={() => this.toggleTab('1')}
                  >
                      Trip Planner
                  </NavLink>
              </NavItem>
              <NavItem>
                  <NavLink
                      className={this.state.tab === '2' ? "active" : "" }
                      onClick={() => this.toggleTab('2')}
                  >
                      Author Section
                  </NavLink>
              </NavItem>
          </Nav>;

    return(
      <Container id="Application">
          {navbar}
        <TabContent activeTab={this.state.tab}>
          <TabPane tabId={'1'}>
            <Info/>
            <Customize trip={this.state.trip} updatePlaces={this.updatePlaces}/>
              <Options options={this.state.trip.options} config={this.state.config} updateOptions={this.updateOptions} port={this.state.port} host={this.state.host} updatePort={this.updatePort} updateHost={this.updateHost}
              updateServer={this.updateServer}/>
            <Card>
              <Trip trip={this.state.trip} updateTrip={this.updateTrip} updateBasedOnResponse={this.updateBasedOnResponse} port={this.state.port} host={this.state.host}/>
              <Search trip={this.state.trip} search={this.state.search} updateBasedOnSearch={this.updateBasedOnSearch} port={this.state.port} host={this.state.host} updateSearch={this.updateSearch}
                      updatePlaces={this.updatePlaces} updateFilter={this.updateFilter} removeFilter={this.removeFilter} config={this.state.config}/>
              <Map trip={this.state.trip} updateTrip={this.updateBasedOnResponse}/>
              <Itinerary trip={this.state.trip} config={this.state.config}/>
            </Card>
            <Distance port={this.state.port} host={this.state.host}/>
          </TabPane>
          <TabPane tabId={'2'}>
              <Bio/>
          </TabPane>
        </TabContent>


      </Container>
    )
  }
}

export default Application;
