const Certificate = ({certiTitle, imgSrc}) => {
    return ( <div className="flex items-center gap-3">
        <img src={imgSrc} className='block rounded-full w-14 aspect-square' alt="" />

        <h5 className="text-headline-26">{certiTitle}</h5>
    </div> );
}
 
export default Certificate;