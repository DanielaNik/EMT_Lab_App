import axios from  '../custom-axios/axios'

const BookService = {
    fetchBooks: () => {
        return axios.get("/books")
    },
    fetchCategories: () => {
        return axios.get("/categories")
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },
    editBook: (id,name,bookCategory,authorName, authorSurname, availableCopies) => {
      return axios.put(`/books/edit/${id}`,{
          "name":name,
          "bookCategory":bookCategory,
          "authorName":authorName,
          "authorSurname":authorSurname,
          "availableCopies":availableCopies
      })
    },
    addBook: (name, bookCategory, authorName, authorSurname, availableCopies) => {
        return axios.post(`/books/add`,{
            "name":name,
            "bookCategory":bookCategory,
            "authorName":authorName,
            "authorSurname":authorSurname,
            "availableCopies":availableCopies
        })
    },
    getBook: (id) => {
        return axios.get(`/books/${id}`)
    },
    borrowBook: (id) => {
        return axios.post(`/books/borrow/${id}`)
    }
}

export default BookService;
