import type {ReactNode} from 'react';
import clsx from 'clsx';
import Heading from '@theme/Heading';
import styles from './styles.module.css';

type FeatureItem = {
  title: string;
  description: ReactNode;
};

const FeatureList: FeatureItem[] = [
  {
    title: 'THB',
    description: (
      <>
        "2,200 students, 60 professors and increasingly more research topics in the fields of computer science and media, technology and business: Brandenburg University of Applied Sciences (THB) is a young, modern university located on the outskirts of Berlin, which provides an excellent learning environment. We are a strong partner in teaching and research, securing skilled workers and guaranteeing technology transfer in the region."
        <br></br><br></br>
        - Technische Hochschule Brandenburg
      </>
    ),
  },
  {
    title: 'Cloud Computing',
    description: (
      <>
        "Cloud computing is the on-demand delivery of IT resources over the Internet with pay-as-you-go pricing. Instead of buying, owning, and maintaining physical data centers and servers, you can access technology services, such as computing power, storage, and databases, on an as-needed basis from a cloud provider."
        <br></br><br></br>
        - Amazon Web Services, Inc.
      </>
    ),
  },
  {
    title: 'CI/CD',
    description: (
      <>
        "Continuous integration (CI) refers to the practice of automatically and frequently integrating code changes into a shared source code repository. Continuous delivery and/or deployment (CD) is a 2 part process that refers to the integration, testing, and delivery of code changes."
        <br></br><br></br>
        - Red Hat
      </>
    ),
  },
];

function Feature({title, description}: FeatureItem) {
  return (
    <div className={clsx('col col--4')}>
      <div className="text--center padding-horiz--md">
        <Heading as="h3">{title}</Heading>
        <p>{description}</p>
      </div>
    </div>
  );
}

export default function HomepageFeatures(): ReactNode {
  return (
    <section className={styles.features}>
      <div className="container">
        <div className="row">
          {FeatureList.map((props, idx) => (
            <Feature key={idx} {...props} />
          ))}
        </div>
      </div>
    </section>
  );
}
