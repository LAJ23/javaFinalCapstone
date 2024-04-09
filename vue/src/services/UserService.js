import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:3000"
});

export default {
    user() {
        return http.get('/user');
    },
    getUser(id) {
        return http.get(`/user/${id}`)
    },
    addUser(user) {
        return http.past('/user', user)
    },
    updateUser(user) {
        return http.put(`user/${user.id}`)
    },
    deleteUser(id) {
        return http.delete(`/user/${id}`)
    }
}