const Comment = ({imgSrc, name, commentDate, content, stars=4}) => {
    return ( <div className="py-5 px-7 border border-grey-900">
        <div className="flex gap-3 items-center">
            <img src={imgSrc} className='block aspect-square rounded-full w-24' alt="" />
            <h5 className="font-bold text-lead-24">{name}</h5>
            
        </div>
        <p className="py-1.5 font-light text-small-16">Commented on {commentDate}</p>

        <p>{content}</p>
    </div> );
}
 
export default Comment;