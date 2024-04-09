import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:3000"
});

export default {
    user() {
        return http.get('/users');
    },
    getUser(id) {
        return http.get(`/users/${id}`)
    },
    getUsername(id) {
        return http.get(`/users/${id}`)
    },
    addUser(username, password) {
        return http.past('/users', username, password)
    },
    updateUser(user) {
        return http.put(`users/${user.id}`)
    },
    deleteUser(id) {
        return http.delete(`/users/${id}`)
    }
}