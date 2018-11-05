import React, {Component} from 'react'
import {Card, CardBody, Button, ButtonGroup, Input, Container, ListGroup, ListGroupItem} from 'reactstrap'
import {request} from "../../api/api";
import {Form, FormGroup, Label, Row, Col} from 'reactstrap'
import Header from "../Marginals/Header";

/* Options allows the user to change the parameters for planning
 * and rendering the trip map and itinerary.
 * The options reside in the parent object so they may be shared with the Trip object.
 * Allows the user to set the options used by the application via a set of buttons.
 */
class Search extends Component {
    constructor(props) {
        super(props);
        this.state = {
            search: this.props.search,
            current: "null"
        }
        this.search = this.search.bind(this);
        this.addPlace = this.addPlace.bind(this);
        this.addPlaceAll = this.addPlaceAll.bind(this);
    }

    search(){
        this.props.search.places = [];
        request(this.props.search, 'search', this.props.port, this.props.host).then(res => {this.props.updateBasedOnSearch(res);});
    }

    addPlace(place){
        let newPlaces = new Array(this.props.trip.places.length + 1);
        for(let index = 0; index < (newPlaces.length); index++){
            if(index === this.props.trip.places.length) {
                newPlaces[index] = JSON.parse(JSON.stringify(this.props.trip.places[0]));
            }
            else
                newPlaces[index] = this.props.trip.places[index];
        }
        newPlaces[newPlaces.length - 1].name = place.name;
        newPlaces[newPlaces.length - 1].latitude = place.latitude;
        newPlaces[newPlaces.length - 1].longitude = place.longitude;
        newPlaces[newPlaces.length - 1].id = place.id;
        this.props.updatePlaces(newPlaces);
    }

    addPlaceAll(){
        for(let index = 0; index < (this.props.search.places.length); index++){
            this.addPlace(this.props.search.places[index]);
        }
    }

    render(){
        let data =
            <ListGroup>
                <Label>Click on a result to add the result to the trip</Label>
                {this.props.search.places.map((place) =>
                    <ListGroupItem
                        tag="button"
                        id={place.name}
                        className='btn-outline-dark unit-button'
                        active={this.state.current === place.name}
                        onClick={((event) => {this.setState({"current": event.target.innerText}); this.addPlace(place)})}
                    >
                        {place.name}
                    </ListGroupItem>
                )
                }
            </ListGroup>

        return(
            <Card>
                <CardBody>
                    <Container>
                    <h3>Search</h3>
                        <Row>
                            <Col>
                                <div>
                                <h5>Input Values For Search</h5>
                                    <div>
                                        <Label>Enter Name</Label>
                                        <Input type="text" placeholder="Name" onChange={(event) => this.props.updateName(event.target.value)}/>
                                        <p/>
                                        <Label>Enter Limit</Label>
                                        <Input type="number" placeholder="Limit" onChange={(event) => this.props.updateLimit(event.target.value)}/>
                                    </div>
                                </div>
                            </Col>
                        </Row>
                      <Row>
                            <Col>
                                <p/>
                                <div className="text-center">
                                        <Button onClick={this.search} className="btn">Search</Button>
                                </div>
                                <p/>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                {data}
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <p/>
                                <div className="text-center">
                                    <Button onClick={(event) => {this.addPlaceAll()}}>Add All</Button>
                                </div>
                           </Col>
                        </Row>
                    </Container>
                </CardBody>
            </Card>
        )
    }
}

export default Search;