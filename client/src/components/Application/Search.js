import React, {Component} from 'react'
import {Card, CardBody, Button, ButtonGroup, Input, Container, ListGroup, ListGroupItem} from 'reactstrap'
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
            search: this.props.search,
            current: "null"
        }
        this.search = this.search.bind(this);
    }

    search(){
        this.props.search.places = [];
        request(this.props.search, 'search', this.props.port, this.props.host).then(res => {this.props.updateBasedOnSearch(res);});
    }

    render(){
        let data =
            <ListGroup>
                {this.props.search.places.map((place) =>
                    <ListGroupItem
                        tag="button"
                        id={place.name}
                        className='btn-outline-dark unit-button'
                        active={this.state.current === place.name}
                        onClick={((event) => this.setState({"current": event.target.innerText}))}
                    >
                        {place.name}
                    </ListGroupItem>
                )}
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
                      <Row>
                            <Col>
                                <div className="text-center">
                                    <Button onClick={this.search} className="btn">Search</Button>
                                </div>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                {data}
                            </Col>
                        </Row>
                    </Container>
                </CardBody>
            </Card>
        )
    }
}

export default Search;