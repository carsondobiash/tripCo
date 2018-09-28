import React, {Component} from 'react'
import {Card, CardBody, Button, Form, Input, FormGroup} from 'reactstrap'

/* Options allows the user to change the parameters for planning
 * and rendering the trip map and itinerary.
 * The options reside in the parent object so they may be shared with the Trip object.
 * Allows the user to set the options used by the application via a set of buttons.
 */
class Trip extends Component{
    constructor(props) {
        super(props);
        this.upload = this.upload.bind(this);
        this.fileOnChange = this.fileOnChange.bind(this);
        this.state = {file: null}
    }

    fileOnChange(event){
        let f = event.target.files[0];
        this.setState({'file': f})
    }

    upload(){
        let file = this.state.file;
        if(file){
            let reader = new FileReader();
            reader.onload =  function(event){
                //console.log(event.target.result);
                let parsed = JSON.parse(event.target.result);
                this.props.updateBasedOnResponse(parsed);
            }.bind(this);
            reader.readAsText(file);
        }
    }




    render() {
        return(
            <Card>
                <CardBody>
                    <p>Trip</p>
                    <Form>
                        <FormGroup>
                            <Input type="file" title="input" onChange={this.fileOnChange}/>
                            <Button type="submit" onClick={this.upload}>Load</Button>
                        </FormGroup>
                    </Form>
                </CardBody>
            </Card>
        )
    }
}

export default Trip;