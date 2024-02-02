<template>
    <button @mouseover="hoverAction" @mouseleave="hoverAction" :style="{background: this.hover ? 'rgb(229, 229, 229)' : color }" @click="btnClick(this.text)" v-show="text">{{ text}} </button>
    <button @mouseover="hoverAction" @mouseleave="hoverAction" :style="{background: this.hover ? 'rgb(229, 229, 229)' : color }" @click="btnClick(this.text)" v-if="text === ''"><i class="fa-solid fa-delete-left"></i></button>
</template>

<script>

import MainInput from './MainInput.vue';

export default{
    name: 'Button',
    data(){
        return{
            hover: false
        }
    },
    props:{
        text: String,
        color: String
    },
    methods: {
        btnClick(text){
            this.$emit('btnClick', text)
        },
        hoverAction(){
            this.hover = !this.hover
        }
    },
    emits: ['btnClick']

}

</script>

<style scoped>

button{
    border: 0 solid black;
    /* background: rgb(225, 225, 225); */
    border-radius: 2%;

}

button:focus{
    outline: none;
    /* background: rgb(229, 229, 229); */
}

button:hover{
    outline: none;
    background: rgb(229, 229, 229);
}

fa-solid fa-delete-left{
    width: 10%;
    height: auto;
    display: flex;
    justify-content: center;
    align-content: center;
}
</style>