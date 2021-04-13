import axios from "../custom-axios/axios";

const LibraryService = {

    fetchBooks : () => {
        return axios.get("/books")
    },

    getBook : (id) => {
        return axios.get(`/books/${id}`)
    },

    getAuthor : (id) => {
        return axios.get(`/authors/${id}`)
    },

    fetchAuthors : () => {
        return axios.get("/authors");
    },

    deleteBook : (id) => {
        return axios.delete(`/books/delete/${id}`)
    },

    addBook : (name,category,availableCopies,authorId) => {
        return axios.post("/books/add", {
            "name" : name,
            "category" : category,
            "availableCopies" : availableCopies,
            "authorId" : authorId
        });
    },

    editBook : (id, name, category, availableCopies, authorId) => {
        return axios.put(`/books/edit/${id}`,{
            "name" : name,
            "category" : category,
            "availableCopies" : availableCopies,
            "authorId" : authorId
        });
    },

    markAsTaken : (id) => {
        return axios.put(`/books/markAsTaken/${id}`)
    }

}

export default LibraryService;