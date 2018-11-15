import React, {Component} from 'react'
import {Card, CardBody, Button, ButtonGroup, Input, Container, ListGroup, ListGroupItem} from 'reactstrap'
import {request} from "../../api/api";
import {Form, FormGroup, Label, Row, Col, ButtonDropdown, DropdownMenu, DropdownItem, DropdownToggle} from 'reactstrap'

/* Options allows the user to change the parameters for planning
 * and rendering the trip map and itinerary.
 * The options reside in the parent object so they may be shared with the Trip object.
 * Allows the user to set the options used by the application via a set of buttons.
 */
class Search extends Component {
    constructor(props) {
        super(props);
        this.state = {
            current: "null",
            store: {name:"", values:[]},
            dropdownOpen: false
        };
        this.search = this.search.bind(this);
        this.addPlace = this.addPlace.bind(this);
        this.addPlaceAll = this.addPlaceAll.bind(this);
        this.storeFilterName = this.storeFilterName.bind(this);
        this.addFilterValue = this.addFilterValue.bind(this);
        this.toggle = this.toggle.bind(this);
        this.removeFilterValue = this.removeFilterValue.bind(this);
    }

    toggle(){
     this.setState({dropdownOpen: !this.state.dropdownOpen});
    }

    search(){
        this.props.search.places = [];
        request(this.props.search, 'search', this.props.port, this.props.host).then(res => {this.props.updateBasedOnSearch(res);});
    }

    addPlace(place){
        this.props.trip.places.push(place);
        this.props.updatePlaces(this.props.trip.places);
    }

    addPlaceAll(){
        for(let index = 0; index < (this.props.search.places.length); index++){
            this.addPlace(this.props.search.places[index]);
        }
    }

    storeFilterName(event){
        let temp = {'name':event.target.value, 'values':[]};
        this.setState({store: temp});
    }

    addFilterValue(event){
        let temp = this.state.store;
        temp.values.push(event.target.value);
        this.setState({store: temp});
    }

    removeFilterValue(event){
        let filterValues = this.state.store;
        filterValues.values.splice(filterValues.values.indexOf(event.target.value),1);
        this.setState({store: filterValues});
    }


    render() {
        let values =
            <div>
                <h5>Input Values For Search</h5>
                <div>
                    <Label>Enter Name</Label>
                    <Input id={'searchName'} type="text" placeholder="Name" onChange={(event) => this.props.updateName(event.target.value)}/>
                    <p/>
                    <Label>Enter Limit</Label>
                    <Input id={'searchLimit'} type="number" placeholder="Limit" onChange={(event) => this.props.updateLimit(event.target.value)}/>
                </div>
            </div>

        let data =
            <ListGroup>
                <Label>Click on a result to add the result to the trip</Label>
                {this.props.search.places.map((place) =>
                    <ListGroupItem
                        key={place.name}
                        tag="button"
                        id={place.name}
                        className='btn-outline-dark unit-button'
                        active={this.state.current === place.name}
                        onClick={((event) => {
                            this.setState({"current": event.target.innerText});
                            this.addPlace(place)
                        })}
                    >
                        {place.name}
                    </ListGroupItem>
                )
                }
            </ListGroup>

        let filter =
            <FormGroup>
            <Label>Filter Name</Label>
        <Input id={'filterName'} type="select" onChange={(event) => this.storeFilterName(event)}>
            <option value=''>Select</option>
            <option key='continent' value='continent'>Continent</option>
            <option key='type' value='type'>Type</option>
        </Input>
        </FormGroup>

        let filterValues;
        if (this.state.store.values != null) {
            filterValues =
            this.state.store.values.map((values) =>
                <DropdownItem key={values} value={values} onClick={(event) => this.removeFilterValue(event)}>{values}</DropdownItem>
            )
        }

        let filterValuesAdd =
            <FormGroup>
                <Label>Filter Values(Click on values to add them)</Label>
                {this.state.store.name === 'type' ?
                    <Input id={'filterType'} type="select" onChange={(event) => this.addFilterValue(event)} multiple>
                        <option value='balloonport'>Balloonport</option>
                        <option value='closed'>Closed</option>
                        <option value='heliport'>Heliport</option>
                        <option value='large_airport'>Large Airport</option>
                        <option value='medium_airport'>Medium Airport</option>
                        <option value='seaplane_base'>Seaplane Base</option>
                        <option value='small_airport'>Small Airport</option>
                    </Input>
                    :
                    this.state.store.name === 'continent' ?
                        <Input id={'filterContinent'} type="select" onChange={(event) => this.addFilterValue(event)} multiple>
                            <option value='Africa'>Africa</option>
                            <option value='Antarctica'>Antarctica</option>
                            <option value='Asia'>Asia</option>
                            <option value='Europe'>Europe</option>
                            <option value='North America'>North America</option>
                            <option value='Oceania'>Oceania</option>
                            <option value='South America'>South America</option>
                        </Input>
                        :
                        <Input type="select" multiple>
                            <option>Select</option>
                        </Input>
                }
            </FormGroup>

        let found;
        if(this.props.search.places != null){
            if(this.props.search.limit === 0){
                found =
                    <Label>Showing {this.props.search.found} out of {this.props.search.found}</Label>
            }
            if(this.props.search.limit !== 0){
                found =
                    <Label>Showing {this.props.search.limit} out of {this.props.search.found}</Label>
            }
            if(this.props.search.limit >= this.props.search.found){
                found =
                    <Label>Showing {this.props.search.found} out of {this.props.search.found}</Label>
            }
        }

        return(
            <Card>
                <CardBody>
                    <Container>
                    <h3>Search</h3>
                        <Row>
                            <Col>
                                {values}
                            </Col>
                            <Col>
                                {filter}
                                {filterValuesAdd}
                                <p/>
                                <div className="text-right">
                                    <Label>View values in filter. Click on a value to remove. Then add the filter.</Label>
                                    <ButtonDropdown id={'filterValues'} isOpen={this.state.dropdownOpen} toggle={this.toggle} className="text-center">
                                        <DropdownToggle caret>
                                            Filter Values
                                        </DropdownToggle>
                                        <DropdownMenu>
                                            {filterValues}
                                        </DropdownMenu>
                                    </ButtonDropdown>
                                    <Button id={'addFilter'} onClick={(event) => this.props.updateFilter(this.state.store)}>Add Filter</Button>
                                    <Button id={'removeFilter'} onClick={(event) => this.props.removeFilter()}>Remove Filters</Button>
                                </div>
                            </Col>
                        </Row>
                      <Row>
                            <Col>
                                <p/>
                                <div className="text-center">
                                        <Button id={'search'} onClick={this.search} className="btn">Search</Button>
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
                                {found}
                                <div className="text-center">
                                    <Button id={'search'} onClick={(event) => {this.addPlaceAll()}}>Add All</Button>
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