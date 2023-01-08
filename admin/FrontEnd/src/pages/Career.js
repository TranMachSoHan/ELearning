import React, { useState } from 'react'

export const Career = () => {
    const data = {
        learning_path: [
            {
                name: 'Python',
                courselist: [
                    {
                        name: "Python for beginnder"
                    }, {
                        name: "Python for junior"
                    }, {
                        name: "Python for senior"
                    }
                ]
            },
            {
                name: 'Java',
                courselist: [
                    {
                        name: "Java for beginnder"
                    }, {
                        name: "Java for junior"
                    }, {
                        name: "Java for senior"
                    }
                ]
            }, {
                name: 'C++',
                courselist: [
                    {
                        name: "C++ for beginnder"
                    }, {
                        name: "C++ for junior"
                    }, {
                        name: "C++ for senior"
                    }
                ]
            }
        ]
    }
    const [state, setState] = useState(data.learning_path[0].name)
    return (
        <div class="flex flex-col mt-5 w-full p-4">
            <div class="flex flex-row p-8 bg-white rounded shadow-sm">
                <div class="flex flex-col border-r border-grey-100 pr-4">
                    {data.learning_path.map((item, key) => (
                        <div class="my-4">
                            <h2 onClick={(e) => { setState(item.name); console.log(state) }} class={"text-base font-black cursor-pointer  group-hover:text-white font-sans" + (state === item.name ? "text-primary-500" : "text-grey-800")}>{item.name}</h2>
                        </div>
                    ))}
                </div>
                <div class="flex flex-col right-0 pl-5">
                    {data.learning_path.map((item, key) => (
                        <div class="my-4">
                            <h2 class="text-base text-grey-800 group-hover:text-white font-sans">item.name</h2>
                        </div>
                    ))}
                </div>

            </div>
        </div>
    )
}
