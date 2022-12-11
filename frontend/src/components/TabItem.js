const TabItem = ({content, isActive, onClick}) => {
    return (
        <h4 onClick={() => {onClick(content)}} className={"text-lead-24 text-grey-900 cursor-pointer " + (isActive ? 'font-bold' :"") }>
            {content}
        </h4>
     );
}
 
export default TabItem;