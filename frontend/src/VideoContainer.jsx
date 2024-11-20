import YouTube from "react-youtube";
import * as PropTypes from "prop-types";

export function VideoContainer(props) {
    const {videoInfo, fetchNextVideo} = props;

    const opts= {
        height: '390',
        width: '640',
        playerVars: {
            autoplay: 1,
            controls: 0,
            disablekb: 1,
            rel: 0,
            iv_load_policy: 3,
        },
    };

    return (
        <div>
            <YouTube videoId={videoInfo["videoId"]} opts={opts}/>
            <div>
                <h1>{videoInfo["title"]}</h1>
                <p>View count: {videoInfo["views"]}</p>
                <p>Release date: {new Date(videoInfo["publishDate"]).toLocaleString()}</p>
                <button onClick={fetchNextVideo}>New video</button>
            </div>
        </div>
    )
}

VideoContainer.propTypes = {
    videoInfo: PropTypes.shape({
        "videoId": PropTypes.string,
        "title": PropTypes.string,
       "publishDate": PropTypes.string,
        "views": PropTypes.number
    }).isRequired,
    fetchNextVideo: PropTypes.func.isRequired
}