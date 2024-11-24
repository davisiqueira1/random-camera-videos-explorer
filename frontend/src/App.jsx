import {VideoContainer} from "./components/video-container/VideoContainer.jsx";
import {Component} from "react";

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            videoDetails: undefined,
        }
    }

    fetchVideoDetails = async () => {
        const API_BASE_URL = 'http://backend:8080';

        const response = await fetch(`${API_BASE_URL}/api/videos/random`,
        {
                headers:{'Content-Type':'application/json'},
                method:"GET"
        })

        if(response.status !== 200){
            // todo: better error handling
            return;
        }

        const data = await response.json();
        this.setState({
            videoDetails: data
        });
    };

    componentDidMount() {
        this.fetchVideoDetails()
    }

    render() {
        if (this.state.videoDetails === undefined) return <div>Loading...</div>

        return (
            <div>
                <VideoContainer videoInfo={this.state.videoDetails} fetchNextVideo={this.fetchVideoDetails}/>
            </div>
        )
    }
}

export default App