import React from 'react'

export const Courses = () => {
    const data = {
        listCourses: [
            {
                courseName: 'Python for Absolute Beginners',
                instructor: 'Hoang Minh Quan'
            },
            {
                courseName: 'Python for Intermediate Students',
                instructor: 'Hoang Minh Quan'
            },
            {
                courseName: 'Python for Senior Students',
                instructor: 'Hoang Minh Quan'
            },
        ]
    }
    return (
        <div class="flex flex-col mt-5 w-full p-4">
            <div class="flex flex-col p-8 bg-white rounded shadow-sm">
                <table class="min-w-full divide-y divide-gray-200 table-auto">
                    <thead class="bg-gray-50">
                        <tr>
                            <th scope="col"
                                class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                Name
                            </th>
                            <th scope="col"
                                class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                Creator
                            </th>
                        </tr>
                    </thead>
                    <tbody class="bg-white">
                        {data.listCourses.map((item, key) => (<tr>
                            <td class="py-4">
                                <div class="flex items-center">
                                    <div class="flex-shrink-0 h-10 w-10">
                                    </div>
                                    <div class="ml-4">
                                        <div class="text-sm font-medium text-gray-900">
                                            {item.courseName}
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                                {item.instructor}
                            </td>
                        </tr>))}
                    </tbody>
                </table>

            </div>
        </div >
    )
}
