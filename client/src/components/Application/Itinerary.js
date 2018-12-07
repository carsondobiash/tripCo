import React, {Component} from 'react'
import {
    CardBody,
    CardText,
    ButtonGroup,
    Navbar,
    NavbarBrand,
    NavbarToggler,
    Card,
    Collapse,
    Container
} from 'reactstrap'
import { Input, Form } from 'reactstrap'
import { Table, Row, Col, Label } from 'reactstrap'

/* Options allows the user to change the parameters for planning
 * and rendering the trip map and itinerary.
 * The options reside in the parent object so they may be shared with the Trip object.
 * Allows the user to set the options used by the application via a set of buttons.
 */
class Itinerary extends Component{
    constructor(props) {
        super(props);
        this.state = {
            name: true,
            id: false,
            latitude: false,
            longitude: false,
            toggler: false
        };
        this.updateTable = this.updateTable.bind(this);
        this.togglerNav = this.togglerNav.bind(this);
    }


    totalDistance(array){
        let total = [array[0]];

        array.map((distance, index) => {
                if (index !== 0) {
                    total[index] = total[index - 1] + distance
                }
            }
        );
        return total;
    }

    updateTable(event){
        this.setState({
            [event.target.value]: event.target.checked
        })
    }

    togglerNav(){
        this.setState({toggler: !this.state.toggler})
    }

    render() {
        const checks = this.props.config.attributes.map((attr) =>
            <div align="left">
                <Label><Input
                    type="checkbox"
                    checked={this.state[attr]}
                    onClick={this.updateTable}
                    value={attr}
                />
                    {attr.charAt(0).toUpperCase() + attr.slice(1)}
                </Label>
            </div>
        );

        return(
            <div>
            <Navbar light>
                <NavbarBrand>Itinerary</NavbarBrand>
                <NavbarToggler onClick={this.togglerNav}/>
            </Navbar>
            <div>
                <Container>
                    <Row>
                        <Col>
                <Collapse isOpen={this.state.toggler}>
            <Card>
            <CardBody>
                <h4>{this.props.trip.title}</h4>
                <Table responsive bordered>
                    <thead>
                    </thead>
                    <tbody>
                        {this.state.id === true &&
                            <tr>
                                <th scope='row'>Id</th>
                                {this.props.trip.places.length > 0 && this.props.trip.places.map(place =>
                                    <td key={place.id}>{place.id}</td>
                                )}
                            </tr>
                        }
                        {this.state.latitude === true &&
                            <tr>
                                <th scope='row'>Latitude</th>
                                {this.props.trip.places.length > 0 && this.props.trip.places.map(place =>
                                    <td key={place.id}>{place.latitude}</td>
                                )}
                            </tr>
                        }
                        {this.state.longitude === true &&
                            <tr>
                                <th scope='row'>Longitude</th>
                                {this.props.trip.places.length > 0 && this.props.trip.places.map(place =>
                                    <td key={place.id}>{place.longitude}</td>
                                )}
                            </tr>
                        }
                        {this.state.name === true &&
                            <tr>
                                <th scope='row'>Name</th>
                                {this.props.trip.places.length > 0 && this.props.trip.places.map(place =>
                                    <td key={place.id}>{place.name}</td>
                                )}
                            </tr>
                        }
                        {this.state.name === true &&
                            <tr>
                                <th scope='row'>Destination</th>
                                {this.props.trip.places.length > 0 && this.props.trip.places.map((place, index) => {
                                        if (index !== 0) {
                                            return <td key={place.id}>{place.name}</td>
                                        }
                                    }
                                )}
                                {this.props.trip.places.length > 0 &&
                                <td key={this.props.trip.places[0].id}>{this.props.trip.places[0].name}</td>}
                            </tr>
                        }
                        <tr>
                            <th scope='row'>Leg Distance</th>
                            {this.props.trip.distances && this.props.trip.distances.map((distance, index) =>
                                <td key={index}>{distance}</td>
                            )}

                        </tr>
                        <tr>
                            <th scope='row'>Total</th>
                            {this.props.trip.distances && this.totalDistance(this.props.trip.distances).map((num, index) =>
                                <td key={index}>{num}</td>
                            )}
                        </tr>
                    </tbody>
                </Table>
                <Col>
                    <Form>
                        {checks}
                    </Form>
                </Col>
            </CardBody>
            </Card>
                </Collapse>
                        </Col>
                    </Row>
                </Container>
            </div>
            </div>
        )
    }
}

export default Itinerary;