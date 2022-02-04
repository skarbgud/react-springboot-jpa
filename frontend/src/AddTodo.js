import React from 'react';
import { TextField, Paper, Button, Grid } from '@material-ui/core';

class AddTodo extends React.Component {
  constructor(props) {
    super(props);
    this.state = { item: { title: '' } };
    this.add = props.add;
  }

  // input에 대한 함수 작성
  onInputChange = (e) => {
    const thisItem = this.state.item;
    thisItem.title = e.target.value;
    this.setState({ item: thisItem });
    console.log(thisItem);
  };
  // + 버튼에 대한 함수 작성 
  onButtonClick = () => {
    this.add(this.state.item);
    this.setState({ item: { title: '' } });
  };
  // 엔터키에 대하 함수 작성
  enterKeyEventHandler = (e) => {
    if (e.key === 'Enter') {
      this.onButtonClick();
    }
  };

  render() {
    return (
      <Paper style={{ margin: 16, padding: 16 }}>
        <Grid container>
          <Grid xs={11} md={11} item style={{ paddingRight: 16 }}>
            <TextField
              placeholder="Add Todo here"
              fullWidth
              onChange={this.onInputChange}
              value={this.state.item.title}
              onKeyPress={this.enterKeyEventHandler}
            />
          </Grid>
          <Grid xs={1} md={1} item>
            <Button
              fullWidth
              color="secondary"
              variant="outlined"
              onClick={this.onButtonClick}
            >
              +
            </Button>
          </Grid>
        </Grid>
      </Paper>
    );
  }
}

export default AddTodo;
