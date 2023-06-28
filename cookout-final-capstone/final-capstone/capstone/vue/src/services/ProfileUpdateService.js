import axios from 'axios';

export default {

  update(user, id) {
    return axios.put(`/myprofile/${id}`, user)
  },

  myProfile(user) {
    return axios.get('/myprofile', user)
  },

  getUserByEmail(emailAddress){
    return axios.get(`/myprofile/${emailAddress}`);
  }
}
