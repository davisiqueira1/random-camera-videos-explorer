import './App.css'
import {VideoContainer} from "./VideoContainer.jsx";
import {Component} from "react";
import {dummyData} from "./utils/dummyData.js";

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            videoDetails: undefined,
        }
    }

    fetchVideoDetails = () => {
        const data = dummyData[Math.floor(Math.random() * dummyData.length)];
        console.log(data);
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