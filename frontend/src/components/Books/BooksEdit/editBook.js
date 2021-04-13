import React from 'react'
import {useHistory} from 'react-router-dom'

const EditBook = (props) => {

    const history = useHistory();

    const [formData, updateFormData] = React.useState({
        name: "",
        availableCopies: 0,
        category: "",
        author: 1
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
        const quantity = formData.availableCopies !== 0 ? formData.availableCopies : props.book.availableCopies;
        const category = formData.category !== "" ? formData.category : props.book.category;
        const author = formData.author !== 0 ? formData.author : props.book.author.id;

        props.onEditBook(props.book.id, name, quantity, category, author);
        history.push("/books");
    }


    return (
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
                               placeholder={props.book.name}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="quantity">Quantity</label>
                        <input type="text"
                               className="form-control"
                               id="quantity"
                               name="availableCopies"
                               placeholder={props.book.availableCopies}
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="quantity">Category</label>
                        <input type="text"
                               className="form-control"
                               id="categories"
                               name="category"
                               placeholder={props.book.category}
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Author</label>
                        <select name="author" className="form-control" onChange={handleChange}>
                            {props.authors.map((term) => {
                                    if ( props.book.author !== undefined && props.book.author.id === term.id )
                                        return <option selected={props.book.author.id}  value={term.id}>{term.name} {term.surname}</option>
                                    else
                                        return <option value={term.id}>{term.name} {term.surname}</option>
                                    }
                            )}
                        </select>
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    );

}

export default EditBook;