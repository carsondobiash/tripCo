import React, {Component} from 'react'
import {
    Card,
    CardBody,
    CardTitle,
    Button,
    Input,
    Navbar,
    NavbarBrand,
    NavbarToggler,
    Collapse,
    Container,
    Row,
    Col
} from 'reactstrap'
import {request} from "../../api/api";
import {Form, FormGroup, Label} from 'reactstrap'

class Distance extends Component{
    constructor(props){
        super(props);
        this.distance = this.distance.bind(this);
        this.updateOriginLat = this.updateOriginLat.bind(this);
        this.updateOriginLong = this.updateOriginLong.bind(this);
        this.updateDestinLat = this.updateDestinLat.bind(this);
        this.updateDestinLong = this.updateDestinLong.bind(this);
        this.toggleNav = this.toggleNav.bind(this);
        this.state = {
            distance:{
                type: "distance",
                origin: {
                    latitude: '',
                    longitude: '',
                    name: "Origin"
                },
                destination: {
                    latitude: '',
                    longitude: '',
                    name: "Destination"
                },
                units: "miles",
                distance: 0
            },
            toggler: false
        };
    }

    distance(){
        let response = request(this.state.distance, 'distance', this.props.port, this.props.host).then(
            res => {this.setState({distance: res})}
        );
    }

    updateOriginLat(value){
        value.preventDefault();
        let lat = this.state.distance.origin;
        lat.latitude = value.target.value;
        this.setState(lat);
    }

    updateOriginLong(value){
        value.preventDefault();
        let long = this.state.distance.origin;
        long.longitude = value.target.value;
        this.setState(long);
    }

    updateDestinLat(value){
        value.preventDefault();
        let lat = this.state.distance.destination;
        lat.latitude = value.target.value;
        this.setState(lat);
    }

    updateDestinLong(value){
        value.preventDefault();
        let long = this.state.distance.destination;
        long.longitude = value.target.value;
        this.setState(long);
    }

    toggleNav(){
        this.setState({toggler: !this.state.toggler})
    }

    render(){

        let distance = (<div>
            <h6>Total Distance: {this.state.distance.distance}</h6>
        </div>);


        return(
            <div>
            <Navbar light>
                <NavbarBrand>Distance Calculator</NavbarBrand>
                <NavbarToggler onClick={this.toggleNav}/>
            </Navbar>
                <Container>
                    <Row>
                        <Col>
                <Collapse isOpen={this.state.toggler}>
            <Card>
                <CardBody>
                    <CardTitle>Find Distance Between Two Points</CardTitle>
                    <Form onSubmit={this.distance}>
                        <FormGroup>
                            <Input type = "number"
                                   placeholder = "Origin Latitude"
                                   value = {this.state.distance.origin.latitude}
                                   onChange = {this.updateOriginLat}/>
                        </FormGroup>
                        <FormGroup>
                            <Input type = "number"
                                   placeholder = "Origin Longitude"
                                   value = {this.state.distance.origin.longitude}
                                   onChange = {this.updateOriginLong}/>
                        </FormGroup>
                        <FormGroup>
                            <Input type = "number"
                                   placeholder = "Destination Latitude"
                                   value = {this.state.distance.destination.latitude}
                                   onChange = {this.updateDestinLat}/>
                        </FormGroup>
                        <FormGroup>
                            <Input type = "number"
                                   placeholder = "Destination Longitude"
                                   value = {this.state.distance.destination.longitude}
                                   onChange = {this.updateDestinLong}/>
                        </FormGroup>
                        <Button>Calculate</Button>
                    </Form>
                    {distance}
                </CardBody>
            </Card>
                </Collapse>
                        </Col>
                    </Row>
                </Container>
            </div>
        )
    }
}

export default Distance;