<template>

  <div class="mainContainer">
      <MainInput :input="this.text"/>

      <div class="btnsContainer">
        
        <Button color="rgba(232, 232, 232, 0.754)" @btnClick="precent" text="%" />
        <Button color="rgba(232, 232, 232, 0.754)" @btnClick="deleteText" text="CE" />
        <Button color="rgba(232, 232, 232, 0.754)" @btnClick="deleteText" text="C" />
        <Button color="rgba(232, 232, 232, 0.754)" @btnClick="deleteLastElement" text=""/>
        <Button color="rgba(232, 232, 232, 0.754)" @btnClick="multiInverse" text="1/x" />
        <Button color="rgba(232, 232, 232, 0.754)" @btnClick="power2" text="x²" />
        <Button color="rgba(232, 232, 232, 0.754)" @btnClick="square2" text="√x" />
        <Button color="rgba(232, 232, 232, 0.754)" @btnClick="btnClk" text="÷" />
        <Button @btnClick="btnClk" text="7" />
        <Button @btnClick="btnClk" text="8" />
        <Button @btnClick="btnClk" text="9" />
        <Button color="rgba(232, 232, 232, 0.754)" @btnClick="btnClk" text="×" />
        <Button @btnClick="btnClk" text="4" />
        <Button @btnClick="btnClk" text="5" /> 
        <Button @btnClick="btnClk" text="6" />
        <Button color="rgba(232, 232, 232, 0.754)" @btnClick="btnClk" text="-" />
        <Button @btnClick="btnClk" text="1" />
        <Button @btnClick="btnClk" text="2" />
        <Button @btnClick="btnClk" text="3" />
        <Button color="rgba(232, 232, 232, 0.754)" @btnClick="btnClk" text="+" />
        <Button @btnClick="changeSign" text="+/-" />
        <Button @btnClick="btnClk" text="0" />
        <Button @btnClick="btnClk" text="." />
        <Button color="lightBlue" @btnClick="calcExpresion" text="=" />
      </div>
  </div>

</template>

<script>

import Button from './components/Button.vue';
import MainInput from './components/MainInput.vue';

export default {
  name: 'App',
  components: {
    MainInput,
    Button
  },
  data(){
    return {
      text: '',
      curNo: '',
      result: ''
    }
  },
  watch: {
    result(){

      let oldNo = this.curNo;

      if( Math.floor( this.result ) == this.result ){
        this.curNo = Math.floor(this.result).toString()
      }else{
        this.curNo = this.result
      }
      console.log(this.curNo)
      if( this.text )
        this.text = this.text.substring(0, this.text.lastIndexOf(oldNo)) + this.curNo;
      else
        this.text = this.curNo;

      if( !this.text )
        this.text = 0

    },
  },
  methods: {
    validInput(){
      if( this.text && this.text !== 'E' && this.curNo )
        return true
      this.text = 'E'
      return false
    },
    deleteText(){
      this.text = '';
      this.curNo = ''
    },
    deleteLastElement(){
      let lastEl = this.text[ this.text.length-1 ]
      this.text = this.text.substring(0, this.text.length-1);

      if( (lastEl >= '0' && lastEl <= '9') || lastEl == '.' )
        this.curNo = this.curNo.substring(0, this.curNo.length-1);
    },
    async calcExpresion(){
      if( this.validInput() && this.text !== this.curNo ){
        await this.postData('http://localhost:8080/calc/math/expression', this.text)
        let res = await this.getData('http://localhost:8080/calc/final/result')

        this.result = res['expression']

        this.text = ''

        if( res['expression'] === 'E' )
          this.text = 'E'
      }
    },
    async btnClk(text){
      if( this.text === 'E' )
        this.text = ''


      if( this.checkoperator(text) && (this.text.includes('+') || this.text.includes('÷') || (this.text.includes('×')) || (this.text.includes('-'))) ){
        if( this.text[ this.text.length-1 ] + text !== '×-' && this.text[ this.text.length-1 ] + text !== '÷-' && this.text[ this.text.length-1 ] + text !== '--' && this.text[ this.text.length-1 ] + text !== '+-')
          await this.calcExpresion()
      }

      this.text += text

      if ( (text >= '0' && text <= '9') || text == '.' ) 
        this.curNo += text
      else if ( text === '-' && ( this.text === '' || this.checkoperator(this.text[this.text.length-1])) )
        this.curNo = '-'
      else
        this.curNo = ''
    },
    checkoperator( el ){
      return el == '+' || el == '×'  ||  el == '÷' || el == '-';
    },
    async precent(){
      if( this.curNo < 0 ){
        this.text = 'E';
        this.curNo = ''

      }
      
      if( this.validInput() ){
        await this.postData('http://localhost:8080/calc/math/expression', this.curNo)

        let res = await this.getData('http://localhost:8080/calc/precent')

        this.result = res['expression']

        if( res['expression'] === 'E' )
          this.text = 'E'
      }
    },
    async multiInverse(){
      if( 1 / this.curNo === Infinity ){
        this.text = 'E';
        this.curNo = '';
        return
      }
      if( this.validInput() ){
        await this.postData('http://localhost:8080/calc/math/expression', this.curNo)

        let res = await this.getData('http://localhost:8080/calc/multi/inverse')

        this.result = res['expression']

        if( res['expression'] === 'E' )
          this.text = 'E'
      }
    },
    async changeSign(){
      if( this.validInput() ){
        await this.postData('http://localhost:8080/calc/math/expression', this.curNo)

        let res = await this.getData('http://localhost:8080/calc/change/sign')

        this.result = res['expression']

        if( res['expression'] === 'E' )
          this.text = 'E'
      }
    },
    async square2(){
      if( this.curNo < 0 ){
        this.text = 'E';
        this.curNo = ''
      }
      if( this.validInput() ){
        await this.postData('http://localhost:8080/calc/math/expression', this.curNo)

        let res = await this.getData('http://localhost:8080/calc/square2')

        this.result = res['expression']

        if( res['expression'] === 'E' )
          this.text = 'E'
      }
    },
    async power2(){
      if( this.validInput() ){
        await this.postData('http://localhost:8080/calc/math/expression', this.curNo)

        let res = await this.getData('http://localhost:8080/calc/power2')

        this.result = res['expression']

        if( res['expression'] === 'E' )
          this.text = 'E'
      }
    },
    async getData(api){
      const result = await fetch(api);

      let finalData = await result.json();

      return finalData;
    },
    async postData(api, data){
      
      let input = data.replaceAll("×","*");
      input = input.replaceAll("÷","/");

      await fetch(api,{
        method: "post",
        headers: {
          'Content-type': "application/json; charset=UTF-8"
        },
        body: JSON.stringify({
          expression: String(input)
        }),
      })
    }
  }
}
</script>

<style>
*{
  margin: 0;
}


.mainContainer{
  position: fixed;
  top: 40%;
  left: 50%;
  transform: translate(-50%, -50%);

  height: 30%;
  width: 20%;
  padding: 0;
  margin: 0 auto;
}

.btnsContainer{
  background:  lightgray;
  display: grid;
  grid-template-columns: auto auto auto auto;
  grid-gap: 0.5%;
  row-gap: 1%;
  width: auto;
  height: 100%;
  min-width: 150px; 
  position: relative;
  top: 0;
  left: 0;
}


#app {
  display: flex;
  justify-content: center;
  align-content: center;
  text-align: center;
  color: #2c3e50;

}
</style>
