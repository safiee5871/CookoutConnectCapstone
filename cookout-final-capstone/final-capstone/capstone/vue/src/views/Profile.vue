<template>
    <div>
        <user-component />
    </div>
</template>

<script>
import UserComponent from '../components/UserComponent.vue';

export default {
    components: { UserComponent },
    name: 'profile',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
        emailAddress: '',
        userNotes: '',
        userId: ''
      },
    }
  }
}
</script>

<style scoped>
.form-input-group {
  margin-bottom: 1rem;
  font-size: 150%;
  width: 800px;
  display: flex;
  justify-content: space-between;
}
label {
  margin-right: 0.5rem;
}

input {
  width: 70%;
}

textarea {
  width: 70%;
  height: 75px
}

.nav-btns {
  display: flex;
  justify-content: space-evenly;
}

.btn2 {
  font-family: "mencken-std", sans-serif;
  font-weight: 400;
  font-style: normal;
  color: #FF415B;
  margin: 0 0 0 15px;
  border: 2px solid #64AA71;
  border-radius: 50px;
  padding: 8px 25px;
  text-transform: uppercase;
  font-size: 13px;
  letter-spacing: 1px;
  transition: 0.3s;
  background: #EAE8E8;
}

.btn2:hover {
  background: #64AA71; 
  color: #EAE8E8;
}
</style>
