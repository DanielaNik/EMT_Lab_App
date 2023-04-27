import React from "react";
import { useNavigate } from 'react-router-dom';

const addBook = (props) => {
    const navigate = useNavigate();

    const [formData, updateFormData] = React.useState({
        "name":"",
        "bookCategory":"",
        "authorName":"",
        "authorSurname":"",
        "availableCopies":0
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name;
        const bookCategory = formData.bookCategory;
        const authorName = formData.authorName;
        const authorSurname = formData.authorSurname;
        const availableCopies = formData.availableCopies;

        props.onAddBook(name,bookCategory,authorName,authorSurname,availableCopies)
        navigate("/books")
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Book name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               required
                               placeholder="Enter product name"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="authorName">Author name</label>
                        <input type="text"
                               className="form-control"
                               id="authorName"
                               name="authorName"
                               placeholder="Author name"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="authorSurname">Author Surname</label>
                        <input type="text"
                               className="form-control"
                               id="authorSurname"
                               name="authorSurname"
                               placeholder="Author Surname"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="availableCopies">Available copies</label>
                        <input type="text"
                               className="form-control"
                               id="availableCopies"
                               name="availableCopies"
                               placeholder="Available copies"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Category</label>
                        <select name="category" className="form-control" onChange={handleChange}>
                            {props.categories.map((term) =>
                                <option value={term}>{term}</option>
                            )}
                        </select>
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )

}

export default addBook;
