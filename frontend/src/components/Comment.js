const Comment = ({imgSrc, name, commentDate, content, stars=4}) => {
    let formatDate = new Date(commentDate)

    return ( <div className="py-5 border px-7 border-grey-900">
        <div className="flex items-center gap-3">
            <img src={imgSrc} className='block w-24 rounded-full aspect-square' alt="" />
            <h5 className="font-bold text-lead-24">{name}</h5>
            
        </div>
        <p className="py-1.5 font-light text-small-16">Commented on {formatDate.toDateString() }</p>

        <p>{content}</p>
    </div> );
}
 
export default Comment;