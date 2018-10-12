import React, {Component} from 'react'
import {Card, CardBody, CardTitle, Button, ButtonGroup, Input} from 'reactstrap'
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
        this.state = {
            distance:{
                type: "distance",
                origin: {
                    latitude: null,
                    longitude: null,
                    name: "Origin"
                },
                destination: {
                    latitude: null,
                    longitude: null,
                    name: "Destination"
                },
                units: "miles",
                distance: 0
            }
        };
    }

    distance(){
        let response = request(this.props.distance, 'distance', this.state.port).then(
            res => {this.props.updateBasedOnResponse(res);}
        );
    }

    updateOriginLat(value){
        let lat = this.state.distance.origin;
        lat.latitude = value.target.value;
        this.setState(lat);
    }

    updateOriginLong(value){
        let long = this.state.distance.origin;
        long.longitude = value.target.value;
        this.setState(long);
    }

    updateDestinLat(value){
        let lat = this.state.distance.destination;
        lat.latitude = value.target.value;
        this.setState(lat);
    }

    updateDestinLong(value){
        let long = this.state.distance.destination;
        long.longitude = value.target.value;
        this.setState(long);
    }



    render(){
        return(
            <Card>
                <Label>Find Distance Between Two Points Below</Label>
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
                    <Button>Submit</Button>
                </Form>
            </Card>
        )
    }
}

export default Distance;