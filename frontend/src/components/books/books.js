import React, {Component} from "react";
import {Link} from "react-router-dom";
import ReactPaginate from "react-paginate";

class Books extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            page: 0,
            size: 5
        }
    }

    render() {
        const offset = this.state.size * this.state.page;
        const nextPageOffset = offset + this.state.size;
        const pageCount = Math.ceil(this.props.books.length / this.state.size);
        const books = this.getBooksOnPage(offset, nextPageOffset);;

            return (
                <div className="container mm-4 mt-5">
                    <div className="row">
                        <table className="table table-striped">
                            <thead>
                            <tr>
                                <th scope="col">Name</th>
                                <th scope="col">Author name</th>
                                <th scope="col">Author surname</th>
                                <th scope="col">Available copies</th>
                            </tr>
                            </thead>
                            <tbody>
                            {books}
                            </tbody>
                        </table>
                        <div className="col mb-3">
                            <div className="row">
                                <div className="col-sm-12 col-md-12">
                                    <Link className={"btn btn-block btn-dark"} to={"/books/add"}>Add new product</Link>
                                </div>
                            </div>
                        </div>
                        <ReactPaginate
                            previousLabel={"back"}
                            nextLabel={"next"}
                            breakLabel={<a href="/#">...</a>}
                            breakClassName={"break-me"}
                            pageClassName={"ml-1"}
                            pageCount={pageCount}
                            marginPagesDisplayed={2}
                            pageRangeDisplayed={5}
                            onPageChange={this.handlePageClick}
                            containerClassName={"pagination m-4 justify-content-center"}
                            activeClassName={"active"}
                        />
                    </div>
                </div>
            );
    }


    handlePageClick = (data) => {
        let selected = data.selected;
        this.setState({
            page: selected
        });
    };

    getBooksOnPage = (offset, nextPageOffset) => {
        return this.props.books
            .map((book, index) => {
                if (index >= offset && index < nextPageOffset) {
                    return (
                        <tr key={book.id}>
                            <td>{book.name}</td>
                            <td>{book.author.name}</td>
                            <td>{book.author.surname}</td>
                            <td>{book.availableCopies}</td>
                            <td className={"text-right"}>
                                <a title={"Delete"} className={"btn btn-danger"}
                                   onClick={() => this.props.onDelete(book.id)}>
                                    Delete
                                </a>
                                <Link className={"btn btn-info ml-2"}
                                      onClick={() => this.props.onEdit(book.id)}
                                      to={`/books/edit/${book.id}`}>
                                    Edit
                                </Link>
                                <a className={"btn btn-primary ml-2"}
                                      onClick={() => this.props.onBorrow(book.id)}>
                                    Mark as taken
                                </a>
                            </td>

                        </tr>
                    );
                } else {
                    return null;
                }
            })
            .filter((book) => book !== null);
    };
}

export default Books;