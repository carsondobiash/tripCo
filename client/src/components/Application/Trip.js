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
        this.updatePort = this.updatePort.bind(this);

        this.state = {
            port: null,
            trip: this.props.trip,
        }
    }

    fileOnChange(event){
        let f = event.target.files[0];
        if(f){
            let reader = new FileReader();
            reader.onload =  function(event){
                console.log(event.target.result);
                let parsed = JSON.parse(event.target.result);
                this.props.updateBasedOnResponse(parsed);
            }.bind(this);
            reader.readAsText(f);
        }
    }

    plan(){
        let response = request(this.props.trip, 'plan', this.state.port).then(
            res => {this.props.updateBasedOnResponse(res);}
        );
    }

    updatePort(event){
        let portValue = event.target.value;
        this.setState({'port': portValue});
    }



    render() {
        return(
            <Card>
                <CardBody>
                    <Label>Select Port Number</Label>
                    <Input type="select" onChange={this.updatePort}>
                        <option></option>
                        <option value="31400">31400</option>
                        <option value="31401">31401</option>
                        <option value="31402">31402</option>
                        <option value="31403">31403</option>
                        <option value="31404">31404</option>
                        <option value="31405">31405</option>
                        <option value="31406">31406</option>
                        <option value="31407">31407</option>
                        <option value="31408">31408</option>
                        <option value="31409">31409</option>
                        <option value="31410">31410</option>
                        <option value="31411">31411</option>
                        <option value="31412">31412</option>
                        <option value="31413">31413</option>
                        <option value="31414">31414</option>
                        <option value="31415">31415</option>
                        <option value="31416">31416</option>
                        <option value="31417">31417</option>
                        <option value="31418">31418</option>
                        <option value="31419">31419</option>
                        <option value="31420">31420</option>
                        <option value="31421">31421</option>
                        <option value="31422">31422</option>
                        <option value="31423">31423</option>
                        <option value="31424">31424</option>
                    </Input>
                <h3>Trip</h3>
                <Row>
                    <Col>
                        <div>
                            <Label>Trip File</Label>
                            <Input type="file" title="input" onChange={this.fileOnChange}/>
                        </div>
                        <div>
                            <Form>
                                <FormGroup>
                                    <Label>Create Your Own Trip</Label>
                                    <Input type="text"
                                           placeholder="Title"/>
                                </FormGroup>
                                <FormGroup>
                                    <Label>Add New Place</Label>
                                    <Input type="text"
                                           placeholder="County"/>
                                    <Input type="text"
                                           placeholder="Name"/>
                                    <Input type="number"
                                           placeholder="Latitude"/>
                                    <Input type="number"
                                           placeholder="Longitude"/>
                                </FormGroup>
                                <ButtonGroup>
                                    <Button className="btn">Add Place</Button>
                                    <Button type="submit">Create Trip</Button>
                                </ButtonGroup>
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