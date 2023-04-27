import React from "react";
import { useNavigate } from 'react-router-dom';

const editBook = (props) => {
    const navigate = useNavigate();

    const [formData, updateFormData] = React.useState({
        "name": props.book.name,
        "bookCategory": props.book.bookCategory,
        "authorName": props.book.authorName,
        "authorSurname": props.book.authorSurname,
        "availableCopies": props.book.availableCopies,
        "categories":props.categories
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name !== "" ? formData.name : props.book.name;
        const bookCategory = formData.bookCategory  !== "" ? formData.bookCategory : props.book.bookCategory;
        const authorName = formData.authorName  !== "" ? formData.authorName : props.book.authorName;
        const authorSurname = formData.authorSurname  !== "" ? formData.authorSurname : props.book.authorSurname;
        const availableCopies = formData.availableCopies  !== 0 ? formData.availableCopies : props.book.availableCopies;

        props.onEditBook(props.book.id,name,bookCategory,authorName,authorSurname,availableCopies)
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
                               placeholder={props.book.name}
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="authorName">Author name</label>
                        <input type="text"
                               className="form-control"
                               id="authorName"
                               name="authorName"
                               placeholder={props.book.authorName}
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="authorSurame">Author Surname</label>
                        <input type="text"
                               className="form-control"
                               id="authorSurname"
                               name="authorSurname"
                               placeholder={props.book.authorSurname}
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
                               placeholder={props.book.availableCopies}
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Category</label>
                        <select name="category" className="form-control" onChange={handleChange}>
                            {props.categories.map((term) =>
                            {
                                if(props.book.bookCategory !== undefined && props.book.bookCategory === term.bookCategory)
                                return <option selected={props.book.bookCategory} value={term.id}>{term.name}</option>
                                else
                                return <option value={term}>{term}</option>
                            }
                            )}
                        </select>
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default editBook;