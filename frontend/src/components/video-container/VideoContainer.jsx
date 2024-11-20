import YouTube from "react-youtube";
import * as PropTypes from "prop-types";

export function VideoContainer(props) {
    let {videoInfo, fetchNextVideo} = props;
    const opts = {
        playerVars: {
            autoplay: 1,
            disablekb: 1,
            rel: 0,
            iv_load_policy: 3,
        },
    };

    return (
        <div id="outer-container">
            <div id="inner-container">
                <YouTube id="player" videoId={videoInfo["videoId"]} opts={opts} onEnd={() => fetchNextVideo()}/>
                <div id="wrapper">
                    <div id="info-container">
                        <h1>
                            {videoInfo["title"]}
                        </h1>
                        <p>
                            View count: {videoInfo["views"]}<br/>
                            Release date: {new Date(videoInfo["publishDate"]).toLocaleString()}
                        </p>
                    </div>
                    <button id="next-button" onClick={fetchNextVideo}>New video</button>
                </div>
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