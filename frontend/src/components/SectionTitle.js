const SectionTitle = ({title}) => {
    return ( <div className="flex items-center gap-5 ">
        <div className="h-0.5 w-24 bg-black"></div>
        <h3 className="font-bold text-headline-31">{title}</h3>
    </div> );
}
 
export default SectionTitle;