import React, {Component} from 'react'
import {Card, CardHeader, CardBody, Label, Input} from 'reactstrap'
import { ButtonGroup, Button, Form, FormGroup} from 'reactstrap'

/* Options allows the user to change the parameters for planning
 * and rendering the trip map and itinerary.
 * The options reside in the parent object so they may be shared with the Trip object.
 * Allows the user to set the options used by the application via a set of buttons.
 */
class Options extends Component{
  constructor(props) {
    super(props);

  }

  render() {
    const buttons = this.props.config.units.map((unit) =>
      <Button
        key={'distance_button_' + unit}
        className='btn-outline-dark unit-button'
        active={this.props.options.units === unit}
        value={unit}
        onClick={(event) => this.props.updateOptions('units', event.target.value)}
      >
        {unit.charAt(0).toUpperCase() + unit.slice(1)}
      </Button>
    );

      const optimizations = this.props.config.optimization.map((options) =>
          <Button
              key={'distance_button_' + options['label']}
              className='btn-outline-dark unit-button'
              active={this.props.options.optimization === options['label']}
              value={options['label']}
              onClick={(event) => this.props.updateOptions('optimization', event.target.value)}
          >
              {options['label']}
          </Button>
      );

      let maps;
      if(this.props.config !== null) {
          maps = this.props.config.maps.map((map) =>
              <Button
                  key={'distance_button_' + map}
                  className='btn-outline-dark unit-button'
                  active={this.props.options.map === map}
                  value={map}
                  onClick={(event) => this.props.updateOptions('map', event.target.value)}
              >
                  {map}
              </Button>
          );
      }



      let customUnits;
    if(this.props.options.units === 'user defined'){
     customUnits =
         <div>
            <Form inline>
                <br />
                <FormGroup className="mb-2 mr-sm-2 mb-sm-0">
                    <input type="text" placeholder="Enter unit name" value={null} onChange={(event)=> this.props.updateOptions('unitName', event.target.value) }/>
                    <input type="number" name="password" placeholder="Enter unit radius" value={null} onChange={(event)=> this.props.updateOptions('unitRadius', event.target.value) }/>
                </FormGroup>
            </Form>
        </div>
    }


        return(
      <Card>
        <CardBody>
          <p>Select the options you wish to use.</p>
          <ButtonGroup size="sm">
              {buttons}
          </ButtonGroup>
            <br></br><br></br>
            <p>Select the level of optimization you wish to use.</p>
            <ButtonGroup>
                {optimizations}
            </ButtonGroup>
            <br></br><br></br>
            <p>Select your map file type</p>
            <ButtonGroup>
                {maps}
            </ButtonGroup>
            {customUnits}
            <p/>
            <div>
                <Label>Enter Port Number</Label>
                <Input type="text" placeholder="Enter Port Number" onChange={this.props.updatePort}/>
                <p/>
                <Label>Enter Hostname</Label>
                <Input type="text" placeholder="Enter Hostname" onChange={this.props.updateHost}/>
            </div>
        </CardBody>
      </Card>
    )
  }
}

export default Options;