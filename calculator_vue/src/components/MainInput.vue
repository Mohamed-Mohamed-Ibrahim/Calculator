<template>
    <!-- <input v-model="text" type="text"> -->
    <p v-text="input"></p>
</template>


<script>

export default{
    name: 'MainInput',
    props: {
        input: String
    },
}

</script>

<style scoped>
p{
    border: 0 solid black;
    text-align: right;
    box-sizing: border-box;
    background-color: lightgrey;
    position: relative;
    top: 0;
    left: 0;
    width: 100%;
    height: 40%;
    font-size: 180%;
    padding: 2%;
    overflow: hidden;
}

input:focus{
    outline: none;
}
</style>