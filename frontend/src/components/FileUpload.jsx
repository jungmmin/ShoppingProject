import Dropzone from 'react-dropzone'
import axiosInstance from '../utils/axios';

// 파일 전송 api(/products/image)
// eslint-disable-next-line react/prop-types
const FileUpload = ({ onImageChange, images }) => {

    const handleDrop = async (files) => {
        let formData = new FormData();

        const config = {
            header: { 'content-type': 'multipart/form-data' }
        }

        formData.append('imageFiles', files[0]);

        try {
            const response = await axiosInstance.post('/products/image', formData, config);
            const imageUrl = response.data.fileName;

            console.log(imageUrl);
            // onImageChange([...images, response.data.fileName]);
            onImageChange([...images, imageUrl]);

        } catch (error) {
            console.error(error);
        }

    }


    // 클릭시 이미지 삭제
    const handleDelete = (image) => {
        const currentIndex = images.indexOf(image);
        let newImages = [...images];
        newImages.splice(currentIndex, 1);
        onImageChange(newImages);
    }


    return (
        <div className='flex gap-4'>

            {/*이미지 업로드 구역*/}
            <Dropzone onDrop={handleDrop}>
                {({ getRootProps, getInputProps }) => (
                    <section
                        className='min-w-[300px] h-[300px] border flex items-center justify-center'
                    >
                        <div {...getRootProps()}>
                            <input {...getInputProps()} />
                            <p className='text-3xl'>+</p>
                        </div>
                    </section>
                )}
            </Dropzone>

            {/*업로드된 이미지 표시*/}
            <div className='flex-grow h-[300px] border flex  items-center justify-center overflow-x-scroll overflow-y-hidden'>
                {images.map(image => (
                    <div key={image}  onClick={() =>handleDelete(image)}>
                        <img
                            className='min-w-[300px] h-[300px]'
                            // src={`${import.meta.env.VITE_SERVER_URL}/${image}`}
                            src={`${image}`}
                            alt={image}
                        />
                    </div>
                ))}
            </div>

        </div>
    )
}

export default FileUpload