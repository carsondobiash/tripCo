import React, {Component} from 'react'
import {Card, CardBody, Button, ButtonGroup, Input, Container} from 'reactstrap'
import {request} from "../../api/api";
import {Form, FormGroup, Label, Row, Col} from 'reactstrap'

/* Options allows the user to change the parameters for planning
 * and rendering the trip map and itinerary.
 * The options reside in the parent object so they may be shared with the Trip object.
 * Allows the user to set the options used by the application via a set of buttons.
 */
class Search extends Component {
    constructor(props) {
        super(props);
        this.state = {
            search: this.props.search
        }
        this.search = this.search.bind(this);
    }

    search(){
        request(this.props.search, 'search', this.props.port, this.props.host).then(res => {this.props.updateBasedOnSearch(res);});
    }

    render(){
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
                                {/*<Label>Enter ID</Label>*/}
                                {/*<Input type="text" placeholder="ID" value={null} onChange={this.updateID}/>*/}
                                    <Label>Enter Name</Label>
                                    <Input type="text" placeholder="Name" value={null} onChange={(event) => this.props.updateName(event.target.value)}/>
                                    <p/>
                                    <Label>Enter Limit</Label>
                                    <Input type="number" placeholder="Limit" value={null} onChange={(event) => this.props.updateLimit(event.target.value)}/>
                                </div>
                            </div>
                        </Col>
                    </Row>
                        <p/>
                    <Row>
                        <Col>
                            <p>
                                <div className="text-center">
                                    <Button onClick={this.search} className="btn">Search</Button>
                                </div>
                            </p>
                        </Col>
                    </Row>
                    </Container>
                </CardBody>
            </Card>
        )
    }
}

export default Search;