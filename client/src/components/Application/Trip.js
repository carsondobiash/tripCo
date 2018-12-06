import React, {Component} from 'react'
import {Card, CardBody, Button, ButtonGroup, Input} from 'reactstrap'
import {request} from "../../api/api";
import {Form, FormGroup, Label, Row, Col} from 'reactstrap'

/* Options allows the user to change the parameters for planning
 * and rendering the trip map and itinerary.
 * The options reside in the parent object so they may be shared with the Trip object.
 * Allows the user to set the options used by the application via a set of buttons.
 */
class Trip extends Component{
    constructor(props) {
        super(props);
        this.fileOnChange = this.fileOnChange.bind(this);
        this.plan = this.plan.bind(this);
        this.addPlace = this.addPlace.bind(this);
        this.updateTitle = this.updateTitle.bind(this);
        this.state = {
            places: []
        }
    }

    fileOnChange(event){
        let f = event.target.files[0];
        if(f){
            let reader = new FileReader();
            reader.onload =  function(event){
                let parsed = JSON.parse(event.target.result);
                if(!parsed.options) {
                    parsed.options = {
                        "units": "miles",
                        "optimization": "none",
                        "map": "svg"
                    }
                }
                console.log(parsed);
                this.props.updateBasedOnResponse(parsed);
            }.bind(this);
            reader.readAsText(f);
        }
    }

    plan(){
        let response = request(this.props.trip, 'plan', this.props.port, this.props.host).then(
            res => {this.props.updateBasedOnResponse(res);}
        );
    }

    updateTitle(event){
        this.props.updateTrip("title", event.target.value);
    }

    addPlace(event){
        event.preventDefault()
        let place = {
            id: "",
            name: "",
            county: "",
            latitude: "",
            longitude: ""
        };

        place.id = event.target.id.value;
        place.name = event.target.name.value;
        place.county = event.target.county.value;
        place.latitude = event.target.latitude.value;
        place.longitude = event.target.longitude.value;


        this.props.updateTrip("places", this.props.trip.places.concat(place));
    }


    render() {
        return(
            <Card>
                <CardBody>
                <h3>Trip</h3>
                <Row>
                    <Col>
                        <div>
                            <Label>Trip File</Label>
                            <Input type="file" title="input" onChange={this.fileOnChange}/>
                        </div>
                        <div>
                            <Label>Create Your Own Trip</Label>
                            <Input type="text"
                                   onChange={this.updateTitle}
                                   placeholder="Title"/>
                            <Form onSubmit={this.addPlace}>
                                <Label>Add New Place</Label>
                                <Input type="text"
                                       name="name"
                                       placeholder="Name"/>
                                <Input type="text"
                                       name="county"
                                       placeholder="County (optional)"/>
                                <Input type="number"
                                       name="id"
                                       placeholder="Id"/>
                                <Input type="number"
                                       name="latitude"
                                       step="0.01"
                                       placeholder="Latitude"/>
                                <Input type="number"
                                       name="longitude"
                                       step="0.01"
                                       placeholder="Longitude"/>
                                <Button className="btn" type="submit">Add Place</Button>
                            </Form>
                        </div>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <div className="text-center">
                            <Button onClick={this.plan} className="btn">Plan</Button>
                        </div>
                    </Col>
                </Row>
            </CardBody>
        </Card>
        )
    }
}

export default Trip;
